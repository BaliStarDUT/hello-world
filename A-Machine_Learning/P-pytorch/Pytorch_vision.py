import torch
import torchvision
import torchvision.transforms as transforms
import matplotlib.pyplot as plt
import numpy as np
from typing import List, Tuple, Optional, Union


class CIFAR10Viewer:
    def __init__(self):
        # 定义数据转换
        self.transform = transforms.Compose([
            transforms.ToTensor(),  # 将PIL图像转换为Tensor
            transforms.Normalize((0.5, 0.5, 0.5), (0.5, 0.5, 0.5))  # 归一化
        ])
        # 加载CIFAR-10训练集
        self.trainset = torchvision.datasets.CIFAR10(root='./data', train=True,
                                            download=True, transform=self.transform)
        self.trainloader = torch.utils.data.DataLoader(self.trainset, batch_size=4,
                                                shuffle=True, num_workers=2)
        # 加载CIFAR-10测试集
        self.testset = torchvision.datasets.CIFAR10(root='./data', train=False,
                                            download=True, transform=self.transform)
        self.testloader = torch.utils.data.DataLoader(self.testset, batch_size=4,
                                                shuffle=False, num_workers=2)

        self.train_images = []
        self.train_labels = []
        self.test_images = []
        self.test_labels = []
        # 定义类别名称
        self.classes = ('plane', 'car', 'bird', 'cat', 'deer', 
                'dog', 'frog', 'horse', 'ship', 'truck')
        # 缓存测试集数据以提高访问效率
        self._cache_data()

    def _cache_data(self):
        """缓存测试集数据到内存"""
        print("正在缓存图片集数据...")
        
        for images, labels in self.trainloader:
            self.train_images.append(images[0])  # 去掉batch维度
            self.train_labels.append(labels[0].item())
        print(f"已缓存训练集 {len(self.train_images)} 张训练图片")

        for images, labels in self.testloader:
            self.test_images.append(images[0])  # 去掉batch维度
            self.test_labels.append(labels[0].item())
        
        print(f"已缓存测试集 {len(self.test_images)} 张测试图片")

    def show_images_by_indices(self, indices: List[int], figsize: Tuple[int, int] = None,imageset: List[torch.Tensor] = None,labels: List[int] = None):
            """显示指定索引的图片"""
            if not indices:
                print("索引列表为空")
                return
            
            # 验证索引范围
            max_idx = len(imageset) - 1
            valid_indices = [idx for idx in indices if 0 <= idx <= max_idx]
            invalid_indices = [idx for idx in indices if idx < 0 or idx > max_idx]
            
            if invalid_indices:
                print(f"无效索引: {invalid_indices}")
            
            if not valid_indices:
                print("没有有效的索引")
                return
            
            # 计算网格大小
            n_images = len(valid_indices)
            if figsize is None:
                figsize = (min(n_images * 2, 20), max(2, (n_images + 9) // 10 * 2))
            
            n_cols = min(10, n_images)
            n_rows = (n_images + n_cols - 1) // n_cols
            
            fig, axes = plt.subplots(n_rows, n_cols, figsize=figsize)
            if n_rows == 1 and n_cols == 1:
                axes = [axes]
            elif n_rows == 1 or n_cols == 1:
                axes = axes.flatten()
            else:
                axes = axes.flatten()
            
            for i, idx in enumerate(valid_indices):
                # 反归一化图片
                img = imageset[idx] / 2 + 0.5
                img_np = img.numpy().transpose(1, 2, 0)
                img_np = np.clip(img_np, 0, 1)
                
                axes[i].imshow(img_np)
                axes[i].set_title(f'#{idx}: {self.classes[labels[idx]]}')
                axes[i].axis('off')
                
                print(f"图片 #{idx}: 类别 = {self.classes[labels[idx]]}")
            
            # 隐藏多余的子图
            for i in range(n_images, len(axes)):
                axes[i].axis('off')
            
            plt.tight_layout()
            plt.savefig(f'cifar10_images_{n_images}images.png')  # 保存图片到本地文件
            plt.show()
    def show_images_by_range(self, start: int, end: int, step: int = 1,imageset: List[torch.Tensor] = None,labels: List[int] = None):
            """显示指定范围的图片"""
            if start < 0:
                start = 0
            if end >= len(imageset):
                end = len(imageset) - 1
            
            indices = list(range(start, end + 1, step))
            self.show_images_by_indices(indices, figsize=(10, 10),imageset=imageset,labels=labels)
    def get_image_info(self, index: int,imageset: List[torch.Tensor] = None,labels: List[int] = None) -> dict:
        """获取指定索引图片的详细信息"""
        if index < 0 or index >= len(imageset):
            return None
        
        return {
            'index': index,
            'label': labels[index],
            'class_name': self.classes[labels[index]],
            'image_shape': imageset[index].shape
        }
    def get_statistics(self,imageset: List[torch.Tensor] = None,labels: List[int] = None) -> dict:
        """获取测试集统计信息"""
        from collections import Counter
        label_counts = Counter(labels)
        
        print("CIFAR-10测试集统计:")
        print("=" * 30)
        for i, class_name in enumerate(self.classes):
            count = label_counts.get(i, 0)
            percentage = (count / len(imageset)) * 100
            print(f"{class_name:>5s}: {count:4d}张 ({percentage:5.1f}%)")
        
        return label_counts
# 使用示例
if __name__ == "__main__":
    viewer = CIFAR10Viewer()
    
    print("\n" + "="*50)
    print("CIFAR-10测试集查看器")
    print("="*50)
    
    # 示例1: 显示前20张图片
    print("\n1. 显示前20张图片:")
    viewer.show_images_by_range(0, 19,imageset=viewer.test_images,labels=viewer.test_labels)
    
    # # 示例2: 显示索引为50-59的图片
    # print("\n2. 显示索引50-59的图片:")
    # viewer.show_images_by_range(50, 59,imageset=viewer.train_images,labels=viewer.train_labels)
    
    # # 示例3: 显示随机10张图片
    # print("\n3. 显示随机10张图片:")
    # viewer.show_random_images(10)
    
    # # 示例4: 显示指定索引的图片
    # print("\n4. 显示指定索引的图片:")
    # viewer.show_images_by_indices([100, 200, 300, 500, 999])
    
    # # 示例5: 搜索特定类别
    # print("\n5. 搜索所有猫的图片:")
    # cat_indices = viewer.search_by_class('cat')
    # if cat_indices:
    #     viewer.show_images_by_indices(cat_indices[:9])  # 显示前9张猫的图片
    
    # 获取统计信息
    print("\n6. 训练集统计:")
    viewer.get_statistics(imageset=viewer.train_images,labels=viewer.train_labels)
    print("\n6. 测试集统计:")
    viewer.get_statistics(imageset=viewer.test_images,labels=viewer.test_labels)

# import torch.nn as nn
# import torch.nn.functional as F

# class Net(nn.Module):
#     def __init__(self):
#         super(Net, self).__init__()
#         # 卷积层1：输入3通道(RGB)，输出6通道，5x5卷积核
#         self.conv1 = nn.Conv2d(3, 6, 5)
#         # 池化层：2x2窗口，步长2
#         self.pool = nn.MaxPool2d(2, 2)
#         # 卷积层2：输入6通道，输出16通道，5x5卷积核
#         self.conv2 = nn.Conv2d(6, 16, 5)
#         # 全连接层1：输入16*5*5，输出120
#         self.fc1 = nn.Linear(16 * 5 * 5, 120)
#         # 全连接层2：输入120，输出84
#         self.fc2 = nn.Linear(120, 84)
#         # 全连接层3：输入84，输出10(对应10个类别)
#         self.fc3 = nn.Linear(84, 10)

#     def forward(self, x):
#         # 第一层卷积+ReLU+池化
#         x = self.pool(F.relu(self.conv1(x)))
#         # 第二层卷积+ReLU+池化
#         x = self.pool(F.relu(self.conv2(x)))
#         # 展平特征图
#         x = x.view(-1, 16 * 5 * 5)
#         # 全连接层+ReLU
#         x = F.relu(self.fc1(x))
#         x = F.relu(self.fc2(x))
#         # 输出层
#         x = self.fc3(x)
#         return x

# # 实例化网络
# net = Net()


# import torch.optim as optim

# # 交叉熵损失函数
# criterion = nn.CrossEntropyLoss()
# # 随机梯度下降优化器
# optimizer = optim.SGD(net.parameters(), lr=0.001, momentum=0.9)


# for epoch in range(10):  # 训练10个epoch
#     running_loss = 0.0
#     for i, data in enumerate(trainloader, 0):
#         # 获取输入数据
#         inputs, labels = data
        
#         # 梯度清零
#         optimizer.zero_grad()
        
#         # 前向传播
#         outputs = net(inputs)
#         # 计算损失
#         loss = criterion(outputs, labels)
#         # 反向传播
#         loss.backward()
#         # 更新权重
#         optimizer.step()
        
#         # 打印统计信息
#         running_loss += loss.item()
#         if i % 2000 == 1999:  # 每2000个mini-batch打印一次
#             print(f'[{epoch + 1}, {i + 1:5d}] loss: {running_loss / 2000:.3f}')
#             running_loss = 0.0

# print('Finished Training')


# correct = 0
# total = 0
# with torch.no_grad():  # 不计算梯度
#     for data in testloader:
#         images, labels = data
#         outputs = net(images)
#         _, predicted = torch.max(outputs.data, 1)
#         total += labels.size(0)
#         correct += (predicted == labels).sum().item()

# print(f'Accuracy on test images: {100 * correct / total:.2f}%')


# class_correct = list(0. for i in range(10))
# class_total = list(0. for i in range(10))
# with torch.no_grad():
#     for data in testloader:
#         images, labels = data
#         outputs = net(images)
#         _, predicted = torch.max(outputs, 1)
#         c = (predicted == labels).squeeze()
#         for i in range(4):
#             label = labels[i]
#             class_correct[label] += c[i].item()
#             class_total[label] += 1

# for i in range(10):
#     print(f'Accuracy of {classes[i]:5s}: {100 * class_correct[i] / class_total[i]:.2f}%')


# # 保存模型参数
# PATH = './cifar_net.pth'
# torch.save(net.state_dict(), PATH)


# # 加载模型
# net = Net()
# net.load_state_dict(torch.load(PATH))

# # 使用模型进行预测
# outputs = net(images)
# _, predicted = torch.max(outputs, 1)
# print('Predicted: ', ' '.join(f'{classes[predicted[j]]:5s}' for j in range(4)))