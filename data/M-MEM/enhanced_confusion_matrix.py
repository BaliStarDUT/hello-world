import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.metrics import confusion_matrix
import matplotlib.patches as patches

class EnhancedConfusionMatrixVisualizer:
    """增强版混淆矩阵可视化工具"""
    
    def __init__(self, figsize=(12, 10)):
        self.figsize = figsize
        
    def create_enhanced_matrix(self, y_true, y_pred, class_names=None, 
                              normalize='true', style='detailed'):
        """
        创建增强版混淆矩阵
        
        Parameters:
        -----------
        y_true : array-like
            真实标签
        y_pred : array-like
            预测标签
        class_names : list, optional
            类别名称
        normalize : str, optional
            归一化方式: 'true', 'pred', 'all', None
        style : str, optional
            可视化样式: 'detailed', 'simple', 'error-focused'
        """
        
        # 计算混淆矩阵
        cm = confusion_matrix(y_true, y_pred)
        
        if normalize == 'true':
            cm_normalized = cm.astype('float') / cm.sum(axis=1)[:, np.newaxis]
            title_suffix = "(按真实值归一化)"
        elif normalize == 'pred':
            cm_normalized = cm.astype('float') / cm.sum(axis=0)[np.newaxis, :]
            title_suffix = "(按预测值归一化)"
        elif normalize == 'all':
            cm_normalized = cm.astype('float') / cm.sum()
            title_suffix = "(总体归一化)"
        else:
            cm_normalized = cm.astype('float')
            title_suffix = "(原始计数)"
            
        if class_names is None:
            class_names = [f"Class {i}" for i in range(len(np.unique(y_true)))]
            
        return self._plot_matrix(cm_normalized, cm, class_names, style, title_suffix)
    
    def _plot_matrix(self, cm_normalized, cm_raw, class_names, style, title_suffix):
        """绘制增强版混淆矩阵"""
        
        fig, ax = plt.subplots(figsize=self.figsize)
        
        if style == 'detailed':
            return self._detailed_style(cm_normalized, cm_raw, class_names, title_suffix, ax)
        elif style == 'error-focused':
            return self._error_focused_style(cm_normalized, cm_raw, class_names, title_suffix, ax)
        else:
            return self._simple_style(cm_normalized, cm_raw, class_names, title_suffix, ax)
    
    def _detailed_style(self, cm_normalized, cm_raw, class_names, title_suffix, ax):
        """详细样式：显示百分比和计数"""
        
        # 创建热力图
        mask = np.zeros_like(cm_normalized, dtype=bool)
        mask[np.diag_indices_from(mask)] = True
        
        # 绘制对角线元素（正确预测）
        sns.heatmap(cm_normalized, mask=~mask, annot=True, fmt='.1%', 
                   cmap='Greens', cbar=False, ax=ax, 
                   xticklabels=class_names, yticklabels=class_names,
                   annot_kws={'size': 11, 'weight': 'bold'})
        
        # 绘制非对角线元素（错误预测）
        sns.heatmap(cm_normalized, mask=mask, annot=True, fmt='.1%', 
                   cmap='Reds', cbar=False, ax=ax,
                   xticklabels=class_names, yticklabels=class_names,
                   annot_kws={'size': 11, 'weight': 'bold'})
        
        # 添加计数信息
        for i in range(len(class_names)):
            for j in range(len(class_names)):
                count = cm_raw[i, j]
                if count > 0:
                    text = ax.text(j + 0.5, i + 0.65, f'({count})', 
                                 ha="center", va="center", fontsize=9, 
                                 color='black', weight='normal')
        
        ax.set_xlabel('预测值', fontsize=12, fontweight='bold')
        ax.set_ylabel('真实值', fontsize=12, fontweight='bold')
        ax.set_title(f'增强版混淆矩阵 {title_suffix}', fontsize=14, fontweight='bold')
        
        # 添加图例
        legend_elements = [
            patches.Patch(color='#2ca02c', label='正确预测'),
            patches.Patch(color='#d62728', label='错误预测')
        ]
        ax.legend(handles=legend_elements, loc='upper left', bbox_to_anchor=(1.02, 1))
        
        plt.xticks(rotation=45, ha='right')
        plt.yticks(rotation=0)
        plt.tight_layout()
        
        return 
    
    def _error_focused_style(self, cm_normalized, cm_raw, class_names, title_suffix, ax):
        """错误聚焦样式：突出显示错误预测"""
        
        # 计算错误率
        error_matrix = np.zeros_like(cm_normalized)
        for i in range(len(class_names)):
            for j in range(len(class_names)):
                if i != j:  # 错误预测
                    total_true = cm_raw[i, :].sum()
                    if total_true > 0:
                        error_matrix[i, j] = cm_raw[i, j] / total_true
        
        # 创建热力图
        im = ax.imshow(error_matrix, cmap='Reds', aspect='auto', vmin=0, vmax=1)
        
        # 添加数值标签
        for i in range(len(class_names)):
            for j in range(len(class_names)):
                value = error_matrix[i, j]
                if value > 0:
                    text = ax.text(j, i, f'{value:.1%})', 
                    # text = ax.text(j, i, f'{value:.1%}\n({cm_raw[i, j]})', 
                                 ha="center", va="center", 
                                 color='white' if value > 0.5 else 'black',
                                 fontweight='bold', fontsize=10)
        
        ax.set_xticks(range(len(class_names)))
        ax.set_yticks(range(len(class_names)))
        ax.set_xticklabels(class_names, rotation=45, ha='right')
        ax.set_yticklabels(class_names)
        
        ax.set_xlabel('预测值', fontsize=12, fontweight='bold')
        ax.set_ylabel('真实值', fontsize=12, fontweight='bold')
        ax.set_title(f'错误预测分析矩阵 {title_suffix}', fontsize=14, fontweight='bold')
        
        # 添加颜色条
        cbar = plt.colorbar(im, ax=ax)
        cbar.set_label('错误率', fontsize=10)
        
        plt.tight_layout()
        
        return fig
    
    def _simple_style(self, cm_normalized, cm_raw, class_names, title_suffix, ax):
        """简洁样式：标准热力图"""
        
        sns.heatmap(cm_normalized, annot=True, fmt='.2%', cmap='Blues',
                   xticklabels=class_names, yticklabels=class_names, ax=ax)
        
        ax.set_xlabel('预测值', fontsize=12)
        ax.set_ylabel('真实值', fontsize=12)
        ax.set_title(f'混淆矩阵 {title_suffix}', fontsize=14)
        
        plt.xticks(rotation=45, ha='right')
        plt.tight_layout()
        
        return fig
    
    def create_comparison_matrix(self, y_true, y_pred_list, model_names, class_names=None):
        """创建多个模型的对比矩阵"""
        
        n_models = len(y_pred_list)
        fig, axes = plt.subplots(1, n_models, figsize=(5*n_models, 4))
        if n_models == 1:
            axes = [axes]
        
        for i, (y_pred, model_name) in enumerate(zip(y_pred_list, model_names)):
            cm = confusion_matrix(y_true, y_pred)
            cm_normalized = cm.astype('float') / cm.sum(axis=1)[:, np.newaxis]
            
            sns.heatmap(cm_normalized, annot=True, fmt='.1%', cmap='Blues',
                       xticklabels=class_names, yticklabels=class_names, ax=axes[i])
            
            axes[i].set_xlabel('预测值', fontsize=10)
            axes[i].set_ylabel('真实值', fontsize=10)
            axes[i].set_title(f'{model_name}\n准确率: {(cm.diagonal().sum()/cm.sum()*100):.1f}%', 
                            fontsize=11, fontweight='bold')
        
        plt.tight_layout()
        return fig

# 快速使用函数
def plot_enhanced_confusion_matrix(y_true, y_pred, class_names=None, 
                                 style='detailed', normalize='true'):
    """快速绘制增强版混淆矩阵"""
    
    visualizer = EnhancedConfusionMatrixVisualizer()
    return visualizer.create_enhanced_matrix(y_true, y_pred, class_names, 
                                           normalize=normalize, style=style)

# Jupyter Notebook 使用示例
def create_interactive_confusion_matrix(y_true, y_pred, class_names=None):
    """创建交互式混淆矩阵（适用于Jupyter）"""
    
    try:
        import plotly.graph_objects as go
        from plotly.subplots import make_subplots
        
        cm = confusion_matrix(y_true, y_pred)
        cm_normalized = cm.astype('float') / cm.sum(axis=1)[:, np.newaxis]
        
        if class_names is None:
            class_names = [f"Class {i}" for i in range(len(np.unique(y_true)))]
        
        # 创建文本标签
        text_labels = np.empty(cm.shape, dtype=object)
        for i in range(cm.shape[0]):
            for j in range(cm.shape[1]):
                text_labels[i, j] = f"{cm_normalized[i, j]:.1%}<br>({cm[i, j]} samples)"
        
        fig = go.Figure(data=go.Heatmap(
            z=cm_normalized,
            x=class_names,
            y=class_names,
            text=text_labels,
            texttemplate="%{text}",
            textfont={"size": 12, "color": "white"},
            colorscale='RdYlGn',
            reversescale=True,
            zmin=0,
            zmax=1
        ))
        
        fig.update_layout(
            title='交互式混淆矩阵',
            xaxis_title='预测值',
            yaxis_title='真实值',
            width=600,
            height=600
        )
        
        return fig
        
    except ImportError:
        print("Plotly未安装，使用matplotlib版本")
        return plot_enhanced_confusion_matrix(y_true, y_pred, class_names)
