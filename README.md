# 1.作品介绍

**风电机组关键设备全寿命周期管理系统**

近年来，在我国风机总装机容量逐年增加的同时，风机运维成本逐年上升，风电机组故障频发，故对风电机组关键设备及备件运行全寿命周期管理。通过使用计算机技术，将风电机组信息信息化，通过javaWeb技术和数据库等技术实现对信息的修改、查询和统计，从而为维护与更换决策提供参考依据，具有降低运维成本、保障风电机组的安全稳定运行的重要意义。

系统采用B/S架构，前端使用Vue相关技术，后端使用SpringBoot搭建服务器，数据库使用Mysql。

**主要子系统功能：**

| 主要子系统       | 功能                               |
| ---------------- | ---------------------------------- |
| 风场监测         | 监测风场发电量等信息               |
| 故障诊断         | 发现故障后，人工诊断后填写故障表单 |
| 设备维修         | 维修员进行故障维修或预防性检修     |
| 设备基础数据管理 | 管理机组库存和新件、返修件库存信息 |
| 分析与统计       | 分析与统计故障、发电量、备件消耗等 |
| 系统设置         | 管理人员信息                       |



# 2.目录介绍

* windSystem_Vue：基于Vue的Web管理系统的设计与实现
* windSystem_SpringBoot：基于SpringBoot的后端代码设计与实现
* images：README文档图片



# 3.系统介绍

## 3.1 登录

1.工作人员与管理员选择相应的身份

2.输入对应的账户名与密码进入对应的系统

3.同时具备记住密码功能

![image-20240401161627262](https://github.com/LiangZii/windSystem/blob/master/images/image-20240401161627262.png)



## 3.2 实时监测/故障检测/设备维修/数据管理

使用Element-UI绘制主要操作界面，通常包括查看、编辑、搜索、添加、删除等操作。

以下为“**实时监测**”功能界面，其余功能在此省略。

![image-20240401161830575](https://github.com/LiangZii/windSystem/blob/master/images/image-20240401161830575.png)



## 3.3 分析与统计

分析与统计提供详细的故障、发电、备件与消耗等统计，并使用Echarts绘制图表提供更加直观的显示。

![image-20240401162131518](https://github.com/LiangZii/windSystem/blob/master/images/image-20240401162131518.png)



# 4.系统设计

## 4.1 数据库设计

数据库表名清单：

![image-20240401162420384](https://github.com/LiangZii/windSystem/blob/master/images/image-20240401162420384.png)



数据库E-R图：

![image-20240401162438076](https://github.com/LiangZii/windSystem/blob/master/images/image-20240401162438076.png)

## 4.2 前端技术

![image-20240401162646283](https://github.com/LiangZii/windSystem/blob/master/images/image-20240401162646283.png)



## 4.3 后端技术

![image-20240401162701002](https://github.com/LiangZii/windSystem/blob/master/images/image-20240401162701002.png)