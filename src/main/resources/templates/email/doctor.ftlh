<!DOCTYPE html>
<html>
<head>
<title>Thông báo</title>
</head>
<body>
	<h3>Xin chào ${name}!, <br>BKHcare xin cảm ơn bạn đã tin tưởng và đặt lịch khám bệnh trên app BKHcare!</h3>

	<p>BKHcare xin thông báo đến bạn các thông tin đăng ký lịch khám như sau:</p>
	<ol>
		<#list booking_info as booking>		
			<li>${booking}</li>			
		</#list>		
	</ol>	
    <p>Bạn hãy kiểm tra các thông tin trên, nếu có sai xót gì xin liên hệ phần "hỗ trợ" tại app BKHcare!</p>
   <p>BKHcare xin gửi đến bạn liên kết xác nhận đặt khám như sau :
    <a style="color:blue" href=`${direct_url}`>Link</a></p>
	<p>Một lần nữa xin cảm ơn bạn đã tin dùng !</p>
</body>
</html>