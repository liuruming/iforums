# 开启验证码 #
jforum默认验证码功能是关闭掉的(iforums默认是打开的)。通过后台的配置不能够打开验证码的功能(我没有找到，如果你知道请告诉我)。<br />
<br />
其实开启很简单。修改SystemGlobals.properties<br />

captcha.registration = true<br />
captcha.posts = true<br />

以上两项设置为tue就可以了。后面的一些参数是验证码图像的大小，不要设置的太小，字体大于图像的大小，有可能生成不了验证码。<br />
<br />
# ########<br />
# Captcha<br />
# ########<br />
captcha.registration = true<br />
captcha.posts = true<br />
captcha.ignore.case = true<br />
<br />
captcha.width = 150<br />
captcha.height = 40<br />
<br />
captcha.min.words = 5<br />
captcha.max.words = 7<br />

captcha.min.font.size = 15<br />
captcha.max.font.size = 25<br />

开启后，如下所示。<br />
<img src='http://iforums.googlecode.com/files/vcode.png'>