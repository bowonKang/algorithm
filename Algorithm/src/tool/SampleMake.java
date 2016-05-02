/**
 * Sample text 파일 작성용
 */

package tool;
public class SampleMake {
	public static void main(String[] args) throws Exception {
		for(int i=0; i<50; i++){
			for(int j=0; j<50; j++){
				System.out.print((int)(Math.random()*60) + " ");
			}
			System.out.println();
		}
	}
}
