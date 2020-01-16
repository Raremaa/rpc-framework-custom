# 基于java socket手写RPC框架(持续更新)
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FRaremaa%2Frpc-framework-custom.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2FRaremaa%2Frpc-framework-custom?ref=badge_shield)

这个项目是一个个人学习向项目,用于理解RPC框架原理与实现,以便于后期更好的了解dubbo等框架的实现.  

参考了很多资料,收获颇多.  

个人理解,所谓RPC(Remote Procedure Call, 远程过程调用)主要包含两块:

- 数据传输协议(通信协议)

- 序列化

而我们所说的http协议本质也是基于TCP(传输层)的一个应用层协议,也是一种数据传输协议,可以用来做RPC,RPC应该是一种编程模型,有很多种表现形式,而这个项目就是使用了java的socket套接字来自定义报文实现数据传输,本质上也是TCP协议的拓展.

## Tag说明

#### origin

- 原始版本 

#### V0.1.0

- 在原始版本上添加zookeeper作为服务中心

## 模块说明

- rpc-server-api: rpc接口(记得install到本地,因为其他项目可能依赖于这个包)
- rpc-server-provider: rpc服务提供者
- rpc-client: rpc客户端(与rpc-server搭配使用)
- rpc-server: rpc服务端(与rpc-client搭配使用)
- rpc-client-zk: rpc客户端(引入zk进行服务注册,与rpc-server-zk搭配使用)
- rpc-server-zk: rpc客户端(引入zk进行服务注册,与rpc-client-zk搭配使用)

## 计划改进的点

- 引入Netty作为高性能NIO框架,取缔系统目前的BIO
- 分析并发中可能会遇到的问题,尝试用JUC中的线程安全容器解决一些问题

## 几个发现

### 利用curator创建的节点有默认值

在用curator操作zk节点创建的时候,我没有指定zk节点的值,但是实际上发现curator会默认吧当前的ip作为值存进去,后面可能会研究一下curator的源码吧,这个理论上也算合理.

## License
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FRaremaa%2Frpc-framework-custom.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2FRaremaa%2Frpc-framework-custom?ref=badge_large)