# 腾讯云智能数智人（TCADH）集成指南

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)
![Java Version](https://img.shields.io/badge/Java-17%2B-orange)

本SDK提供与腾讯云智能数智人服务的集成能力，支持数字人创建、语音合成、视频生成等核心功能。

-[腾讯云官方文档地址](#https://cloud.tencent.com/document/product/1240)

## 目录
- [功能特性](#功能特性)
- [快速开始](#快速开始)
    - [环境要求](#环境要求)
    - [安装依赖](#安装依赖)
    - [基础用法](#基础用法)
- [核心功能](#核心功能)
    - [数字人管理](#数字人管理)
    - [语音合成](#语音合成)
    - [视频生成](#视频生成)
    - [任务管理](#任务管理)
- [开发指南](#开发指南)
    - [配置说明](#配置说明)
    - [请求模型](#请求模型)
    - [响应处理](#响应处理)
    - [错误代码](#错误代码)
- [最佳实践](#最佳实践)
- [常见问题](#常见问题)

## 功能特性
✅ **数字人管理**
- 创建/查询/更新数字人形象
- 管理数字人音色、服装、姿态配置

✅ **语音合成服务**
- 支持SSML标签的文本驱动合成
- 多音色选择（男声20种/女声23种）
- 语速/音量/情感参数调节

✅ **视频生成服务**
- 模板视频驱动（MP4/MOV/AVI）
- 支持透明通道视频输出
- 实时进度查询（含失败重试机制）

✅ **任务管理系统**
- 异步任务状态跟踪
- 回调通知机制
- 并发资源管理（共享/独享模式）

## 快速开始
### 环境要求
- JDK 17+
- Maven 3.6+
- Spring Boot 2.7+

### 安装依赖
xml <dependency> <groupId>com.etc.tencent</groupId> <artifactId>tencent-cloud-aiperson</artifactId> <version>1.2.0</version> </dependency>
### 基础用法
java // 初始化客户端 TencentAIConfig config = new TencentAIConfig() .setSecretId("your-secret-id") .setSecretKey("your-secret-key") .setRegion("ap-guangzhou");
TencentAiAudioApi apiClient = new TencentAIClient(config).getAudioApi();
// 创建语音合成请求 AudioGenerationReq request = AudioGenerationReq.builder() .virtualmanKey("vm_001") .inputSsml("<speak>欢迎使用智能数智人服务</speak>") .speed(1.2f) .build();
// 执行请求 TencentCloudRestResponse<AudioResult> response = apiClient.synthesizeAudio(request);
## 核心功能
### 数字人管理
java // 查询数字人资产 VirtualmanQueryReq queryReq = VirtualmanQueryReq.builder() .virtualmanType("avatar") .build();
List<VirtualmanAsset> assets = apiClient.queryVirtualmanAssets(queryReq);
### 视频生成
java VideoGenerationReq videoReq = VideoGenerationReq.builder() .efVideoUrl("https://.../template.mp4") .driverType(DriverType.TEXT) .inputSsml("<speak>企业宣传视频内容</speak>") .concurrencyType(ConcurrencyType.EXCLUSIVE) .build();
String taskId = apiClient.createVideoTask(videoReq).getTaskId();
## 开发指南
### 配置说明
| 配置项 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|-----|
| secretId | String | 是 | - | API密钥ID |
| secretKey | String | 是 | - | API密钥Key | 
| region | String | 是 | ap-guangzhou | 服务地域 |
| timeout | int | 否 | 30000 | 请求超时(ms) |

### 错误代码
| 代码 | 含义 | 处理建议 |
|------|------|----------|
| 4000 | 参数校验失败 | 检查请求模型约束 |
| 4101 | 身份验证失败 | 检查SecretId/SecretKey |
| 4300 | 资源不足 | 调整并发类型或联系扩容 |
| 5000 | 服务端错误 | 重试或联系技术支持 |

## 最佳实践
1. **音视频对齐策略**
   java // 设置视频参数一致性 videoReq.setVideoParametersConsistent(1);
2. **失败重试机制**
   java RetryConfig retryConfig = new RetryConfig() .setMaxAttempts(3) .setBackoff(1000);
   apiClient.enableRetry(retryConfig);
3. **回调处理**
4. java @PostMapping("/callback") public void handleCallback(@RequestBody TaskCallback callback) { if (callback.getStatus() == TaskStatus.FAIL) { log.error("任务失败: {}", callback.getFailMessage()); } }

## 常见问题
**Q: 如何选择合适的并发类型？**
- 实时性要求高 → 使用`EXCLUSIVE`独享并发
- 成本敏感 → 使用`SHARED`共享并发

**Q: SSML文本有哪些限制？**
- 最大长度：20,000字符（普通视频）/2,000字符（透明MOV）
- 需要转义XML特殊字符
- 支持`<break>`, `<emphasis>`等标签

**Q: 合成失败常见原因？**
1. 输入文件URL不可达
2. 音视频格式不兼容
3. 文本包含敏感词
4. 资源配额不足
