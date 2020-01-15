# 基于java socket手写RPC框架(持续更新)
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FRaremaa%2Frpc-framework-custom.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2FRaremaa%2Frpc-framework-custom?ref=badge_shield)


## Tag说明

- origin : 原始版本 


## 收获

参考了很多资料,收获颇多.

个人理解,所谓RPC(Remote Procedure Call, 远程过程调用)主要包含两块:

- 数据传输协议(通信协议)

- 序列化

而我们所说的http协议本质也是基于TCP(传输层)的一个应用层协议,也是一种数据传输协议,可以用来做RPC,RPC应该是一种编程模型,有很多种表现形式,而这个项目就是使用了java的socket套接字来自定义报文实现数据传输,本质上也是TCP协议的拓展.

## 可以改进的点

可以改进的点太多了,比如里面的Map的类型都没有使用JUC包中的类,扩展性不够强等等等,也是想通过这个项目方便以后去学习dubbo等开源框架的实现原理,慢慢来吧.

## License
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FRaremaa%2Frpc-framework-custom.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2FRaremaa%2Frpc-framework-custom?ref=badge_large)