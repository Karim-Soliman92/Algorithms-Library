import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class GUMIAndSongsDiv2
{
	int N;
	int [] d;
	int [] t;
	
	public int count(int n, int rem, int bitmask){
		n = n%N;
		if (rem<=0)
			return 0;
		int max = 0;
		for (int i=0;i<N;i++)
			if (rem>=d[i]+Math.abs(t[i]-t[n]))
				max = Math.max(max, count(n, rem-(d[i]+Math.abs(t[i]-t[n])), bitmask|(1<<n)));
		if ((bitmask&(1<<n))==0)
			return max+1;
		else return max;
	}
	
	public int maxSongs(int[] dur, int[] tone, int T)
	{
		N = dur.length;
		d = dur;
		t = tone;
		int max = 0;
		for (int i=0;i<N;i++)
			if (dur[i]<=T)
			max = Math.max(max, count(i,T-dur[i], 1<<i));
		return max;
	}
	
	// BEGIN KAWIGIEDIT TESTING
	// Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
	private static boolean KawigiEdit_RunTest(int testNum, int[] p0, int[] p1, int p2, boolean hasAnswer, int p3) {
		System.out.print("Test " + testNum + ": [" + "{");
		for (int i = 0; p0.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(p0[i]);
		}
		System.out.print("}" + "," + "{");
		for (int i = 0; p1.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(p1[i]);
		}
		System.out.print("}" + "," + p2);
		System.out.println("]");
		GUMIAndSongsDiv2 obj;
		int answer;
		obj = new GUMIAndSongsDiv2();
		long startTime = System.currentTimeMillis();
		answer = obj.maxSongs(p0, p1, p2);
		long endTime = System.currentTimeMillis();
		boolean res;
		res = true;
		System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
		if (hasAnswer) {
			System.out.println("Desired answer:");
			System.out.println("\t" + p3);
		}
		System.out.println("Your answer:");
		System.out.println("\t" + answer);
		if (hasAnswer) {
			res = answer == p3;
		}
		if (!res) {
			System.out.println("DOESN'T MATCH!!!!");
		} else if ((endTime - startTime) / 1000.0 >= 2) {
			System.out.println("FAIL the timeout");
			res = false;
		} else if (hasAnswer) {
			System.out.println("Match :-)");
		} else {
			System.out.println("OK, but is it right?");
		}
		System.out.println("");
		return res;
	}
	public static void main(String[] args) {
		boolean all_right;
		all_right = true;
		
		int[] p0;
		int[] p1;
		int p2;
		int p3;
		
		// ----- test 0 -----
		p0 = new int[]{3,5,4,11};
		p1 = new int[]{2,1,3,1};
		p2 = 17;
		p3 = 3;
		all_right = KawigiEdit_RunTest(0, p0, p1, p2, true, p3) && all_right;
		// ------------------
		
		// ----- test 1 -----
		p0 = new int[]{100,200,300};
		p1 = new int[]{1,2,3};
		p2 = 10;
		p3 = 0;
		all_right = KawigiEdit_RunTest(1, p0, p1, p2, true, p3) && all_right;
		// ------------------
		
		// ----- test 2 -----
		p0 = new int[]{1,2,3,4};
		p1 = new int[]{1,1,1,1};
		p2 = 100;
		p3 = 4;
		all_right = KawigiEdit_RunTest(2, p0, p1, p2, true, p3) && all_right;
		// ------------------
		
		// ----- test 3 -----
		p0 = new int[]{10,10,10};
		p1 = new int[]{58,58,58};
		p2 = 30;
		p3 = 3;
		all_right = KawigiEdit_RunTest(3, p0, p1, p2, true, p3) && all_right;
		// ------------------
		
		// ----- test 4 -----
		p0 = new int[]{8,11,7,15,9,16,7,9};
		p1 = new int[]{3,8,5,4,2,7,4,1};
		p2 = 14;
		p3 = 1;
		all_right = KawigiEdit_RunTest(4, p0, p1, p2, true, p3) && all_right;
		// ------------------
		
		// ----- test 5 -----
		p0 = new int[]{5611,39996,20200,56574,81643,90131,33486,99568,48112,97168,5600,49145,73590,3979,94614};
		p1 = new int[]{2916,53353,64924,86481,44803,61254,99393,5993,40781,2174,67458,74263,69710,40044,80853};
		p2 = 302606;
		p3 = 8;
		all_right = KawigiEdit_RunTest(5, p0, p1, p2, true, p3) && all_right;
		// ------------------
		
		if (all_right) {
			System.out.println("You're a stud (at least on the example cases)!");
		} else {
			System.out.println("Some of the test cases had errors.");
		}
	}
	// END KAWIGIEDIT TESTING
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!