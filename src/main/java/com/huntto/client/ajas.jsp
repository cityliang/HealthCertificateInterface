<!doctype html>  
<html lang="en">  
 <head>  
  <title>Ajax调用方式</title>  
  <script type="text/javascript">  
    function queryMobile(){  
        //创建XMLHttpRequest对象  
        var xhr = new XMLHttpRequest();  
        //打开链接  
        xhr.open("post","http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx",true);  
        //设置content-type  
        xhr.setRequestHeader("content-type","text/xml;charset=utf-8");  
  
        //设置回调函数  
        xhr.onreadystatechange=function(){  
            //判断客户端发送成功&&服务端响应成功  
            if(4 == xhr.readyState && 200 == xhr.status){  
                alert(xhr.responseText);  
            }  
        }  
  
        //组织SOAP协议数据  
        var soapXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"  
        +"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"  
            +"<soap:Body>"  
            +"<getMobileCodeInfo xmlns=\"http://WebXml.com.cn/\">"  
                +"<mobileCode>"+document.getElementById("phoneNum").value+"</mobileCode>"  
              +"<userID></userID>"  
            +"</getMobileCodeInfo>"  
         +" </soap:Body>"  
        +"</soap:Envelope>";  
        alert(soapXML);  
  
        //发送请求  
        xhr.send(soapXML);  
    }  
  </script>  
 </head>  
 <body>  
    手机号归属地查询：<input type="text" id="phoneNum" /><input type="button" value="查询" onclick="javascript:queryMobile();"/>  
 </body>  
</html> 
