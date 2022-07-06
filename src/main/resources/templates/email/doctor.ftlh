<!DOCTYPE html>
<html>
<head>
<title>Thông báo</title>
</head>
<body>
	<h3>Xin chào ${name}!, <br>Heath care xin cảm ơn bạn đã tin tưởng và đặt lịch khám bệnh trên app Health care!</h3>

	<p>Healcare xin thông báo đến bạn các thông tin đăng ký lịch khám như sau:</p>
	<ol>
		<#list booking_info as booking>		
			<li>${booking}</li>			
		</#list>		
	</ol>	
    <p>Bạn hãy kiểm tra các thông tin trên, nếu có sai xót gì xin liên hệ phần "hỗ trợ" tại app Heal Care!</p>
   <p>Health Care xin gửi đến bạn liên kết xác nhận đặt khám như sau :
    <a style="color:blue" href=`${direct_url}`>Link</a></p>
	<p>Một lần nữa xin cảm ơn bạn đã tin dùng !</p>
</body>
</html>