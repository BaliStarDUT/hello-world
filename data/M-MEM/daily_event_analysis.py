import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from datetime import datetime, timedelta
import numpy as np
import warnings
warnings.filterwarnings('ignore')

class DailyEventAnalyzer:
    def __init__(self, dataframe):
        """
        初始化每日事件分析器
        
        参数:
        dataframe: 包含事件数据的DataFrame
        """
        self.df = dataframe.copy()
        self.daily_counts = None
        
    def prepare_data(self, datetime_col='sys_created_at'):
        """
        准备数据，确保时间列格式正确
        
        参数:
        datetime_col: 时间戳列名，默认为'sys_created_at'
        """
        # 检查并转换时间格式
        if datetime_col not in self.df.columns:
            print(f"列 {datetime_col} 不存在，请检查列名")
            return False
            
        # 转换时间格式
        if self.df[datetime_col].dtype == 'object':
            self.df[datetime_col] = pd.to_datetime(self.df[datetime_col])
        elif str(self.df[datetime_col].dtype).startswith('int'):
            # 假设是Unix时间戳
            self.df[datetime_col] = pd.to_datetime(self.df[datetime_col], unit='s')
        
        # 设置时间列为索引
        self.df = self.df.sort_values(datetime_col)
        self.datetime_col = datetime_col
        
        return True
    
    def calculate_daily_counts(self, datetime_col='sys_created_at'):
        """
        计算每日事件数量
        
        参数:
        datetime_col: 时间戳列名
        
        返回:
        每日事件数量的Series
        """
        if not self.prepare_data(datetime_col):
            return None
            
        # 提取日期部分
        self.df['date'] = self.df[datetime_col].dt.date
        
        # 按天统计事件数量
        self.daily_counts = self.df['date'].value_counts().sort_index()
        
        return self.daily_counts
    
    def plot_daily_line(self, figsize=(15, 8), save_path=None, style='seaborn'):
        """
        绘制每日事件数量折线图
        
        参数:
        figsize: 图形大小
        save_path: 保存路径
        style: 绘图风格
        """
        if self.daily_counts is None:
            self.calculate_daily_counts()
            
        if self.daily_counts is None or len(self.daily_counts) == 0:
            print("没有数据可供绘制")
            return
            
        # 设置绘图风格
        # plt.style.use(style)
        
        fig, ax = plt.subplots(figsize=figsize)
        
        # 绘制折线图
        ax.plot(self.daily_counts.index, self.daily_counts.values, 
                linewidth=2, marker='o', markersize=4, 
                color='#1f77b4', alpha=0.8)
        
        # 设置标题和标签
        ax.set_title('每日事件创建数量趋势', fontsize=16, fontweight='bold', pad=20)
        ax.set_xlabel('日期', fontsize=12)
        ax.set_ylabel('事件数量', fontsize=12)
        
        # 格式化x轴
        fig.autofmt_xdate()
        
        # 添加网格
        ax.grid(True, alpha=0.3, linestyle='--')
        
        # 添加统计信息
        max_day = self.daily_counts.idxmax()
        max_count = self.daily_counts.max()
        min_day = self.daily_counts.idxmin()
        min_count = self.daily_counts.min()
        avg_count = self.daily_counts.mean()
        
        # 在图上标注最大值
        ax.annotate(f'最大值: {max_count}', 
                    xy=(max_day, max_count), 
                    xytext=(10, 10), textcoords='offset points',
                    bbox=dict(boxstyle='round,pad=0.3', facecolor='yellow', alpha=0.7),
                    arrowprops=dict(arrowstyle='->', connectionstyle='arc3,rad=0'))
        
        # 添加统计文本框
        stats_text = f'总天数: {len(self.daily_counts)}\n'
        stats_text += f'总事件数: {self.daily_counts.sum()}\n'
        stats_text += f'平均每日: {avg_count:.1f}\n'
        stats_text += f'最大日: {max_count} ({max_day})\n'
        stats_text += f'最小日: {min_count} ({min_day})'
        
        ax.text(0.02, 0.98, stats_text, 
                transform=ax.transAxes,
                verticalalignment='top',
                bbox=dict(boxstyle='round', facecolor='lightgray', alpha=0.8))
        
        # 旋转x轴标签
        plt.xticks(rotation=45)
        
        plt.tight_layout()
        
        if save_path:
            plt.savefig(save_path, dpi=300, bbox_inches='tight')
        
        plt.show()
    
    def plot_with_trend(self, window=7, figsize=(15, 8), save_path=None):
        """
        绘制带移动平均趋势的折线图
        
        参数:
        window: 移动平均窗口大小（天）
        figsize: 图形大小
        save_path: 保存路径
        """
        if self.daily_counts is None:
            self.calculate_daily_counts()
            
        # 计算移动平均
        rolling_mean = self.daily_counts.rolling(window=window).mean()
        
        fig, ax = plt.subplots(figsize=figsize)
        
        # 绘制原始数据
        ax.plot(self.daily_counts.index, self.daily_counts.values, 
                linewidth=1, color='lightgray', alpha=0.7, label='原始数据')
        
        # 绘制移动平均
        ax.plot(rolling_mean.index, rolling_mean.values, 
                linewidth=2.5, color='red', label=f'{window}天移动平均')
        
        ax.set_title(f'每日事件数量趋势 ({window}天移动平均)', fontsize=16, fontweight='bold', pad=20)
        ax.set_xlabel('日期', fontsize=12)
        ax.set_ylabel('事件数量', fontsize=12)
        ax.legend()
        ax.grid(True, alpha=0.3, linestyle='--')
        
        plt.xticks(rotation=45)
        plt.tight_layout()
        
        if save_path:
            plt.savefig(save_path, dpi=300, bbox_inches='tight')
        
        plt.show()
    
    def plot_weekly_pattern(self, figsize=(12, 6), save_path=None):
        """
        绘制每周模式分析
        
        参数:
        figsize: 图形大小
        save_path: 保存路径
        """
        if self.daily_counts is None:
            self.calculate_daily_counts()
            
        # 转换为DataFrame并添加星期信息
        df_counts = pd.DataFrame({'date': self.daily_counts.index, 
                                 'count': self.daily_counts.values})
        df_counts['date'] = pd.to_datetime(df_counts['date'])
        df_counts['weekday'] = df_counts['date'].dt.day_name()
        df_counts['weekday_num'] = df_counts['date'].dt.dayofweek
        
        # 按星期统计
        weekly_avg = df_counts.groupby('weekday_num')['count'].mean()
        weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
        
        fig, ax = plt.subplots(figsize=figsize)
        
        bars = ax.bar(range(7), weekly_avg.values, 
                      color=['skyblue' if i < 5 else 'lightcoral' for i in range(7)])
        
        ax.set_xticks(range(7))
        ax.set_xticklabels(weekdays, rotation=45)
        ax.set_title('每周事件创建数量模式', fontsize=16, fontweight='bold', pad=20)
        ax.set_ylabel('平均事件数量', fontsize=12)
        ax.grid(True, alpha=0.3, axis='y')
        
        # 添加数值标签
        for bar in bars:
            height = bar.get_height()
            ax.text(bar.get_x() + bar.get_width()/2., height,
                    f'{height:.1f}', ha='center', va='bottom')
        
        plt.tight_layout()
        
        if save_path:
            plt.savefig(save_path, dpi=300, bbox_inches='tight')
        
        plt.show()
    
    def get_summary_stats(self):
        """
        获取汇总统计信息
        
        返回:
        包含详细统计信息的字典
        """
        if self.daily_counts is None:
            self.calculate_daily_counts()
            
        stats = {
            '时间范围': f"{self.daily_counts.index.min()} 到 {self.daily_counts.index.max()}",
            '总天数': len(self.daily_counts),
            '总事件数': self.daily_counts.sum(),
            '平均每日事件数': round(self.daily_counts.mean(), 2),
            '中位数每日事件数': round(self.daily_counts.median(), 2),
            '标准差': round(self.daily_counts.std(), 2),
            '最大值': self.daily_counts.max(),
            '最大值日期': self.daily_counts.idxmax(),
            '最小值': self.daily_counts.min(),
            '最小值日期': self.daily_counts.idxmin(),
            '峰值系数': round(self.daily_counts.max() / self.daily_counts.mean(), 2)
        }
        
        return stats
    
    def identify_anomalies(self, threshold=2.0):
        """
        识别异常日期
        
        参数:
        threshold: 异常检测阈值（标准差的倍数）
        
        返回:
        异常日期的DataFrame
        """
        if self.daily_counts is None:
            self.calculate_daily_counts()
            
        mean_val = self.daily_counts.mean()
        std_val = self.daily_counts.std()
        
        # 识别异常值
        anomalies = self.daily_counts[
            (self.daily_counts > mean_val + threshold * std_val) |
            (self.daily_counts < mean_val - threshold * std_val)
        ]
        
        return pd.DataFrame({
            '日期': anomalies.index,
            '事件数量': anomalies.values,
            '异常类型': ['高异常' if x > mean_val else '低异常' for x in anomalies.values]
        })

# 快速使用函数
def plot_daily_events(data, datetime_col='sys_created_at', 
                     figsize=(15, 8), save_path=None):
    """
    快速绘制每日事件数量折线图
    
    参数:
    data: DataFrame数据
    datetime_col: 时间戳列名
    figsize: 图形大小
    save_path: 保存路径
    
    返回:
    分析器实例
    """
    analyzer = DailyEventAnalyzer(data)
    analyzer.calculate_daily_counts(datetime_col)
    
    # 打印统计信息
    stats = analyzer.get_summary_stats()
    print("每日事件统计摘要:")
    print("="*50)
    for key, value in stats.items():
        print(f"{key}: {value}")
    
    # 绘制图表
    analyzer.plot_daily_line(figsize=figsize, save_path=save_path)
    
    # 识别异常
    anomalies = analyzer.identify_anomalies()
    if not anomalies.empty:
        print("\n异常日期:")
        print(anomalies)
    
    return analyzer

# 使用示例
if __name__ == "__main__":
    # 示例数据
    date_range = pd.date_range(start='2024-01-01', end='2024-01-31', freq='D')
    np.random.seed(42)
    daily_counts = np.random.poisson(lam=50, size=len(date_range)) + np.sin(np.arange(len(date_range)) * 0.1) * 20
    
    sample_data = pd.DataFrame({
        'sys_created_at': np.random.choice(date_range, size=1000, p=daily_counts/daily_counts.sum())
    })
    
    # 运行分析
    analyzer = plot_daily_events(sample_data, save_path='daily_events.png')
