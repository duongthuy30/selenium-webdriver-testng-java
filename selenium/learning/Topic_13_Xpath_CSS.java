package learning;

public class Topic_13_Xpath_CSS {

	// div[@attribute-name='attribute-value']//tagname[@attribute-name='attribute-value']

//	 Nên chọn locator nào để chạy:
//	- chọn duy nhất
//	- có id/class/name có nghĩa 
//	- ưu tiên id/class/name vì chạy nhanh 
//	- link ko dùng attribute href nên dùng title hoặc text> vì cần đáp ứng nhiều môi trường

//	Lưu ý:
	// - ko sử dụng tagname với dấu *> chạy chậm dễ hiểu lầm không biết locator là
	// gì
	// -ko nên sd attribute value nằm trong dấu """""
	// Khi 1 element không duy nhất, không định danh được thì cần lấy từ node cha
//	<a href=""/desktops"">Desktops
	// a[href='desktops'
	// thì có thể viết như này
	// a[text()='Desktops']

//	CÚ pháp Xpath function:
	// tagname[text()='attribute value']
	// tagname[string()='attribute value']

//	Lấy tương đối (,) > chạy chậm hơn vì quét phạm vi rộng hơn
	// tagname[contains(text(),'value')]
	// tagname[contains(@attribute,'value')]

//	starts-withs(): lấy text/attribute bắt đầu
	// tagname[starts-with(@attribute,'value')]

	// text()='value'
	// tagname[text()='value']
//	- lấy tuyệt đối, text nằm trong chính thẻ đó 
//	- text nằm ở đầu hoặc cuối đều lấy được( so với thẻ con) 
//	- lấy tuyệt đối> không có xuống dòng, có kí tự trắng ở đầu hoặc cuối

//	contains[text(),'value'] :
	// tagname[contains(text(),'value')]
//	- lấy tương đối, text thuộc node đó
//	- dạng nested text nhưng text phải ở đầu tiên
//	- text nằm trực tiếp trong thẻ con thì ko lấy được
//	- kí tự có trimspace vẫn lấy được
//	- ko dùng contains(text()='value') mà dùng contains(text(),'value')

//	contains(string()) và contains(.) là như nhau :
	// tagname[contains(string(),'value')]
//	- lấy được tất cả 
//	-hạn chế sử dụng vì chạy lâu
//	- nếu bắt buộc sd thì dùng string 

//	concat() nếu text 
	// tagname[text()=concat('Hello ""John"", What',""'s happened?')]"
	// nếu text/value mà kí tự nháy đơn thì dùng nháy đôi bọc lại
	// nếu text/value mà kí tự nháy đôi thì dùng nháy đơn bọc lại

//	and và or là toán tử 
	// tagname[[@attribute='value']"
	// thường sd AND để kết hợp các ĐK để lọc ra element duy nhất

//	not: phủ định lại điều kiện 
	// tagname[not@attribute='value']

//	inside parent : cùng 1 cha
	// tagname[@attribute='value'][index] index: số

//	outside parent:khác cha
//	(//tagname[@attribute=''])[index]

//			last() lấy ra cuối cùng
//			(//tagname[@attribute='value'])[last()]

//			position(): tương tự index
//			//tagname[@attribute='value']/tagname[position()= vị trí ]	

//			axes: luôn luôn check hiện tại mình đang ở node nào 
//			cần có 1 key để định danh
	// tagname[@attribute='value']parent::tagname
//			cha   parent::
//			anh   preceding-sibling::
//			em   following-sibling::
//				axes nếu sp thay đổi vị trí vẫn chạy được 
//				ít bị ảnh hưởng test script ít bị fail
//				có thể kết hợp dynamic locator

//				xpath function hay dùng:
//				- concat()
//				-contains()
//				- string()
//				- text()
//				- starts-with()				
				
				//div/input[@id='email']             	                     div>input[id='email']
				//ul//input[@id='email']	                                 ul input[id='email']
				//input[@id='email']	                                     input[id='email']                     
//				                                                             input#email> nên dùng
//				                                                             #email"
				//div[@class='col-1 new-user']	                             div[class='col-1 new-user']
//				                                                             div.col-1 new-user lấy toàn bộ
//				                                                             div.new-user    lấy 1 phần
//				                                                             .new-user"
				//input[@id='email' and @name='login(username)']	         input[id='emal'][name='login(username)']
				//input[contains(@title,'Email')]	                         input[title*='Email']
				//a[starts-with(@title,'Create an')]	                     a[title^='Create an']
//				end-with không hỗ trợ	                                     a[title$='an Account']
				//ol[@id='selectable']/li[3]  > nth children	             ol[id='selectable']>li:nth-child(3)
				//ol[@id='selectable']/li[1]  > first children	             ol[id='selectable']>li:first-child
				//ol[@id='selectable']/li[12]  > last children	             ol[id='selectable']>li:last-child
//				//div[@class='col-1 new-user']/parent::div                   ko sp 
				//div[@class='col-1 new-user']/following-sibling::div	     div.new-user~div
//				                                                             div.new-users+div"
//				Nếu 1 anh có 1 em thì 2 cách như nhau	
//				 '+' lấy ra 1 người em liền kề	
//				~ lấy ra tất cả các em 	
				//input[@id='emal' or @id='login']	                         input#email,input#login
				//tagname[not@attribute='value']	                         tagname:not([attribute='value'])
			
//				Trước khi test auto cần test manual qua ít nhất 1 lần
//				- Chức năng hiện tại còn lỗi/ đã stable rồi để làm auto
//				- Nắm được business của chức năng> estimate hết bn time
//				- control được business> verify 1 chức năng đúng hay sai"					
				
//	setTimeout (() => {debugger;}, (3000));  ----> 3s sau thì bật debug

//	Test tính năng clear form:
//		- nhập thông tin > verify > reset> verify lại
//           ("input#name).value	

//				Ấn vào 1 element
//				var loginButton = document.querySelector(""button#send2""); loginButton.click();
//				$(""button#send2"").click();	

//				Verify giá trị mà element bị ẩn/che khuất:
//				jquery: $(""css"").text				
}
