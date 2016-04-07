### net.jforum.util.legacy.clickstream.ClickstreamFilter.java ###

这是jforum这个论坛在web.xml里面配置的唯一的一个filter，他的作用是用来判断前来访问的请求是否是一个机器人，比如说各种搜索引擎的bot，具体的判断是用BotChecker.java这个类的isBot方法来判断的。
### Java代码 ###
```
   1. public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,  
   2.         ServletException  
   3. {  
   4.     // Ensure that filter is only applied once per request.  
   5.     if (request.getAttribute(FILTER_APPLIED) == null) {  
   6.         request.setAttribute(FILTER_APPLIED, Boolean.TRUE);  
   7.           
   8.         String bot = BotChecker.isBot((HttpServletRequest)request);  
   9.           
  10.         if (bot != null && log.isDebugEnabled()) {  
  11.             log.debug("Found a bot: " + bot);  
  12.         }  
  13.           
  14.         request.setAttribute(ConfigKeys.IS_BOT, Boolean.valueOf(bot != null));  
  15.     }  
  16.       
  17.     // Pass the request on  
  18.     chain.doFilter(request, response);  
  19. }  
```

net.jforum.util.legacy.clickstream.BotChecker.java

这是ClickstreamFilter中所使用到的一个工具类，它会获取clickstream-jforum.xml中所有配置的bot名称，通过与request.getHeader("User- Agent")，请求头中的User-Agent的名称进行比较返回出具体的bot类型。

net.jforum.util.legacy.clickstream.config.ConfigLoader.java

这是一个用来获取xml中内容的类，BotChecker.java中使用到的clickstream-jforum.xml就是通过它来解析的。ConfigLoader.java的解析方式是通过SAX的方式解析的
### Java代码 ###
```
   1. private ClickstreamConfig config;  
   2.   
   3. SAXParser parser = SAXParserFactory.newInstance().newSAXParser();  
   4.   
   5. parser.parse(fileInput, new ConfigHandler());  
   6.   
   7.     private class ConfigHandler extends DefaultHandler  
   8.     {  
   9.         public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException  
  10.         {  
  11.             if (qName.equals("bot-host")) {  
  12.                 config.addBotHost(attributes.getValue("name"));  
  13.             }  
  14.             else if (qName.equals("bot-agent")) {  
  15.                 config.addBotAgent(attributes.getValue("name"));  
  16.             }  
  17.         }  
  18.     }  
```
（以上的代码经过我修改，并不是源码，只是为了方便看，方便理解）

net.jforum.util.legacy.clickstream.config.ClickstreamConfig.java

这只是个值对象，其中用了两个List用来保存clickstream-jforum.xml中的bot的Host和Agent

在学习了这个Filter后学习到了用SAX的方式在java中解析xml的简单方法。在每个请求都会在头信息当中都会标注User-Agent，通过IE提交的请求会有IE的User-Agent，FF会有FF的User- Agent，google的机器人在抓取网页信息时提交的请求也会包含它的User-Agent。通过判断它的值可以知道访问者是人还是蜘蛛，还可以知道访问者的浏览器类型。