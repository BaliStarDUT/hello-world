import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.preprocessing import StandardScaler
from sklearn.decomposition import PCA
from sklearn.model_selection import train_test_split
import warnings
warnings.filterwarnings('ignore')

class MemoryPCAnalyzer:
    def __init__(self, dataframe):
        """
        初始化PCA分析器
        
        参数:
        dataframe: 包含特征的训练数据集
        """
        self.df = dataframe.copy()
        self.features = None
        self.scaled_data = None
        self.pca = None
        self.explained_variance_ratio = None
        self.cumulative_variance = None
        
    def prepare_data(self, target_column=None):
        """
        准备数据用于PCA分析
        
        参数:
        target_column: 目标列名，如果有的话
        """
        # 选择数值型特征
        numeric_cols = self.df.select_dtypes(include=[np.number]).columns
        
        if target_column and target_column in numeric_cols:
            self.features = [col for col in numeric_cols if col != target_column]
        else:
            self.features = list(numeric_cols)
            
        # 检查缺失值
        if self.df[self.features].isnull().sum().sum() > 0:
            print("警告：数据中存在缺失值，将进行填充...")
            self.df[self.features] = self.df[self.features].fillna(self.df[self.features].mean())
            
        return self.features
    
    def scale_data(self):
        """标准化数据"""
        scaler = StandardScaler()
        self.scaled_data = scaler.fit_transform(self.df[self.features])
        print(f"数据已标准化，特征数量: {len(self.features)}")
        return self.scaled_data
    
    def fit_pca(self, n_components=None):
        """
        执行PCA分析
        
        参数:
        n_components: 要保留的主成分数量，如果为None则保留所有
        """
        if self.scaled_data is None:
            self.prepare_data()
            self.scale_data()
            
        self.pca = PCA(n_components=n_components)
        self.pca.fit(self.scaled_data)
        
        # 计算解释方差比
        self.explained_variance_ratio = self.pca.explained_variance_ratio_
        self.cumulative_variance = np.cumsum(self.explained_variance_ratio)
        
        return self.pca
    
    def plot_explained_variance(self, figsize=(10, 6)):
        """绘制解释方差图"""
        if self.explained_variance_ratio is None:
            print("请先运行fit_pca()")
            return
            
        plt.figure(figsize=figsize)
        
        # 子图1：解释方差比例
        plt.subplot(1, 2, 1)
        bars = plt.bar(range(1, len(self.explained_variance_ratio) + 1), 
                        self.explained_variance_ratio * 100, 
                        alpha=0.7, color='steelblue')
        plt.xlabel('主成分')
        plt.ylabel('解释方差比例 (%)')
        plt.title('各主成分解释方差比例')
        plt.xticks(range(1, len(self.explained_variance_ratio) + 1))
        
        # 添加数值标签
        for bar, ratio in zip(bars, self.explained_variance_ratio):
            height = bar.get_height()
            plt.text(bar.get_x() + bar.get_width()/2., height,
                    f'{ratio*100:.1f}%', ha='center', va='bottom', fontsize=8)
        
        # 子图2：累计解释方差
        plt.subplot(1, 2, 2)
        plt.plot(range(1, len(self.cumulative_variance) + 1), 
                self.cumulative_variance * 100, 
                marker='o', linewidth=2, markersize=6, color='darkred')
        plt.xlabel('主成分数量')
        plt.ylabel('累计解释方差比例 (%)')
        plt.title('累计解释方差比例')
        plt.grid(True, alpha=0.3)
        
        # 添加95%参考线
        plt.axhline(y=95, color='r', linestyle='--', alpha=0.7, label='95%阈值')
        plt.legend()
        
        plt.tight_layout()
        plt.savefig('pca_explained_variance.png', dpi=300, bbox_inches='tight')
        plt.show()
        
    def get_optimal_components(self, threshold=0.95):
        """获取达到阈值所需的主成分数量"""
        if self.cumulative_variance is None:
            print("请先运行fit_pca()")
            return None
            
        n_components = np.argmax(self.cumulative_variance >= threshold) + 1
        print(f"要达到{threshold*100}%的累计解释方差，需要{n_components}个主成分")
        return n_components
    
    def transform_data(self, n_components=None):
        """转换数据到主成分空间"""
        if self.pca is None:
            self.fit_pca(n_components)
            
        transformed_data = self.pca.transform(self.scaled_data)
        
        # 创建新的DataFrame
        if n_components is None:
            n_components = transformed_data.shape[1]
            
        pca_columns = [f'PC{i+1}' for i in range(n_components)]
        pca_df = pd.DataFrame(transformed_data, columns=pca_columns)
        
        return pca_df
    
    def plot_2d_projection(self, target_column=None):
        """绘制前两个主成分的2D投影"""
        pca_df = self.transform_data(n_components=2)
        
        plt.figure(figsize=(10, 8))
        
        if target_column and target_column in self.df.columns:
            # 如果有目标变量，按类别着色
            scatter = plt.scatter(pca_df['PC1'], pca_df['PC2'], 
                                c=self.df[target_column], cmap='viridis', 
                                alpha=0.7, s=50)
            plt.colorbar(scatter, label=target_column)
        else:
            # 无目标变量，单一颜色
            plt.scatter(pca_df['PC1'], pca_df['PC2'], 
                       alpha=0.7, s=50, color='steelblue')
            
        plt.xlabel(f'PC1 ({self.explained_variance_ratio[0]*100:.1f}%)')
        plt.ylabel(f'PC2 ({self.explained_variance_ratio[1]*100:.1f}%)')
        plt.title('前两个主成分的2D投影')
        plt.grid(True, alpha=0.3)
        plt.savefig('pca_2d_projection.png', dpi=300, bbox_inches='tight')
        plt.show()
        
    def get_feature_contributions(self, top_n=10):
        """获取特征在主成分上的贡献度"""
        if self.pca is None:
            print("请先运行fit_pca()")
            return
            
        # 获取前两个主成分的特征权重
        components = self.pca.components_[:2]
        
        # 创建贡献度DataFrame
        contributions = pd.DataFrame(
            components.T,
            columns=['PC1', 'PC2'],
            index=self.features
        )
        
        # 取绝对值并排序
        pc1_top = contributions['PC1'].abs().sort_values(ascending=False).head(top_n)
        pc2_top = contributions['PC2'].abs().sort_values(ascending=False).head(top_n)
        
        print(f"对PC1贡献最大的{top_n}个特征:")
        print(pc1_top)
        print(f"\n对PC2贡献最大的{top_n}个特征:")
        print(pc2_top)
        
        return contributions
    
    def plot_feature_contributions(self, top_n=15):
        """绘制特征贡献度热图"""
        contributions = self.get_feature_contributions(top_n)
        
        # 选择前n个最重要的特征
        top_features = contributions.abs().sum(axis=1).sort_values(ascending=False).head(top_n).index
        contributions_top = contributions.loc[top_features]
        
        plt.figure(figsize=(12, 8))
        sns.heatmap(contributions_top.T, 
                   cmap='coolwarm', 
                   center=0,
                   annot=True, 
                   fmt='.2f',
                   cbar_kws={'label': '特征权重'})
        plt.title('特征在主成分上的贡献度')
        plt.tight_layout()
        plt.savefig('pca_feature_contributions.png', dpi=300, bbox_inches='tight')
        plt.show()

# 使用示例
def run_pca_analysis(train_dataset, target_column=None):
    """
    运行完整的PCA分析流程
    
    参数:
    train_dataset: 训练数据集
    target_column: 目标变量列名（可选）
    
    返回:
    pca_analyzer: PCA分析器实例
    transformed_data: 降维后的数据
    """
    
    # 创建分析器
    analyzer = MemoryPCAnalyzer(train_dataset)
    
    # 准备数据
    features = analyzer.prepare_data(target_column)
    print(f"分析的特征: {features}")
    
    # 标准化数据
    analyzer.scale_data()
    
    # 执行PCA
    analyzer.fit_pca()
    
    # 绘制解释方差图
    analyzer.plot_explained_variance()
    
    # 获取最优主成分数量
    optimal_n = analyzer.get_optimal_components(0.95)
    
    # 2D投影
    analyzer.plot_2d_projection(target_column)
    
    # 特征贡献度分析
    analyzer.get_feature_contributions()
    analyzer.plot_feature_contributions()
    
    # 转换数据
    if optimal_n:
        transformed_data = analyzer.transform_data(optimal_n)
    else:
        transformed_data = analyzer.transform_data(2)
    
    return analyzer, transformed_data

# 在Jupyter Notebook中使用
if __name__ == "__main__":
    # 假设train_dataset已经加载
    # analyzer, pca_data = run_pca_analysis(train_dataset, target_column='your_target_column')
    pass
