package Tech;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {

	@Test
	public void TC_01() {
		//Thư viện Assert: Kiểm tra tính đúng đắn của dữ liệu
		//Mong đợi nó đúng/sai/đầu vào đầu ra như nhau 
		//Bằng null/ khác null
		Assert.assertEquals(false, null);
		
		//Kiểm tra 1 điều kiện mong đợi là luôn luôn đúng
		Assert.assertTrue(false);
		
		//Kiểm tra dữ liệu null hay ko
		Object fullname = null;
		Assert.assertNull(fullname);
		Assert.assertNotNull(fullname);
		
	}
	
}
