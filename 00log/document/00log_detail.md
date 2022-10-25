# 一、加强篇(日志篇)

# 1. 日志文件

## 	1）什么是日志文件

​				日志文件是用于记录系统操作事件的记录文件或文件集合，可分为事件日志和消息日志

## 	2）日志文件的作用

​				具有处理历史数据、诊断问题的追踪以及理解系统的活动等重要作用

## 	3）调试日志

​				调试也就是debug 可以在程序运行中暂停程序运行，可以查看程序在运行中的情况。

​				日志主要是为了更方便的去重现问题

## 	4）系统日志

​				系统日志是记录系统中硬件、软件和系统问题的信息，同时还可以监视系统中发生的事件			

# 2.  JAVA日志框架

​				JUL（java util logging）、logback、log4j、log4j2

​				JCL（Jakarta Commons Logging）、slf4j（ Simple Logging Facade for Java）

## 	1）日志门面

​			JCL、slf4j

## 	2）日志实现

​			JUL、logback、log4j、log4j2

# 3. 日志框架操作

## 1） JUL

​	JUL是JDK自带的一个日志

### 	1. JUL架构

​	![image-20221020103156074](D:\yjxz\Review_outline\yjxz\background\strengthen\00log\document\assets\image-20221020103156074.png)

```
1. Loggers：被称为记录器，应用程序通过获取Logger对象，调用其API来来发布日志信息。Logger通常时应用程序访问日志系统的入口程序。

2. Appenders：也被称为Handlers，每个Logger都会关联一组Handlers，Logger会将日志交给关联Handlers处理，由Handlers负责将日志做记录。Handlers在此是一个抽象，其具体的实现决定了日志记录的位置可以是控制台、文件、网络上的其他日志服务或操作系统日志等。3. 

3. Layouts：也被称为Formatters，它负责对日志事件中的数据进行转换和格式化。Layouts决定了数据在一条日志记录中的最终形式。

4. Level：每条日志消息都有一个关联的日志级别。该级别粗略指导了日志消息的重要性和紧迫，我可以将Level和Loggers，Appenders做关联以便于我们过滤消息。

5. Filters：过滤器，根据需要定制哪些信息会被记录，哪些信息会被放过。

用户使用Logger来进行日志记录，
Logger持有若干个Handler，日志的输出操作是由Handler完成的。
在Handler在输出日志前，会经过Filter的过滤，判断哪些日志级别过滤放行哪些拦截，
Handler会将日志内容输出到指定位置（日志文件、控制台等）。
Handler在输出日志时会使用Layout，将输出内容进行排版
```

### 	2. jul中定义的日志级别

```
* java.util.logging.Level中定义了日志的级别： 
	SEVERE（最高值） 
	WARNING 
	INFO （默认级别） 
	CONFIG 
	FINE 
	FINER 
	FINEST（最低值）
* 还有两个特殊的级别： 
	OFF，可用来关闭日志记录。 
	ALL，启用所有消息的日志记录。
```

### 	3. jul中Logger之间的父子关系

​			JUL中Logger之间存在父子关系，这种父子关系通过树状结构存储，

​			JUL在初始化时会创建一个顶层RootLogger作为所有Logger父Logger，存储上作为树状结构的根节点。

​			并父子关系通过路径来关联

### 	4. jul中日志的配置文件

​			默认配置文件路径$JAVAHOME\jre\lib\logging.properties

### 	5. jul日志原理解析

```
1. 初始化LogManager
	1. LogManager加载logging.properties配置
	2. 添加Logger到LogManager
2. 从单例LogManager获取Logger
3. 设置级别Level，并指定日志记录LogRecord
4. Filter提供了日志级别之外更细粒度的控制
5. Handler是用来处理日志输出位置
6. Formatter是用来格式化LogRecord的
```

![image-20221020105009046](D:\yjxz\Review_outline\yjxz\background\strengthen\00log\document\assets\image-20221020105009046.png)
