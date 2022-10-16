import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptPassword {
	MessageDigest md5;
	
	public CryptPassword(){
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getCryptPassword(String pass){
		byte[]hash = md5.digest(pass.getBytes());
		
		StringBuilder builder = new StringBuilder();
		for(byte b: hash){
			builder.append(String.format("%02X", b));
		}
		
		
		return builder.toString();
	}
	
	
	public boolean checkPassword(String outPass, String inPass){
		byte[]hash = md5.digest(outPass.getBytes());
		
		System.out.println("11111111111111111111111111111111111111111111111111111");
		StringBuilder builder = new StringBuilder();
		for(byte b: hash){
			builder.append(String.format("%02X", b));
		}
		
		if(builder.toString() == inPass) {
			return true;
		}
		
		return false;
	}
	
}
