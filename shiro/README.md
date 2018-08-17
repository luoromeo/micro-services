# shiro 
本例子基于张开涛的《跟我学Shiro》教程后编写的示例代码:
config包包含了shiro的基本配置，具体可以看类中的注释。
在实际使用中还需要考虑几点:

1、shiro的SecurityUtils.getSubject()是根据session来判断的，但是在app和小程序中
是没有seesion的，因此需要自定义自己的session进行管理。可以参考:http://www.cnblogs.com/zhuxiaojie/p/7809767.html。
本项目实现类:StatelessSessionManager

2、分布式系统中需要考虑session的共享

3、分布式系统中需要使用分布式缓存来缓存角色和权限信息