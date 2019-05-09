import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

	private static final char []specialDelimiters = {'^','$','.','|','?','*','+','(','[','{','}',')',']','\\'};

	public static void main(String[] args) {
		ReadStringInput();
	}

	private static void ReadStringInput() {
		String inputString = "";
		int result = 0;
		String temp ="", temp1 = "";
		int loop = 1;
		boolean delimiterflag = false;
		String []delimiter = new String [10];
		String pattern3 = "";
		String spliterString = "";
		String tempdelimiter = "";
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the Input String : ");
		while(sc.hasNextLine())
		{		
			temp = sc.nextLine();
			temp1 = temp;
			if(temp.isEmpty() || temp.isBlank())
				break;
			else
			{
				inputString += temp;
				inputString += '\n';
				
				if(inputString.startsWith("//"))
				{
				while (loop == 1)
				{
					delimiterflag = Pattern.matches("^//.*\n$",inputString);
					if(delimiterflag == true)
					{
						temp = inputString.substring(2, inputString.length()-1); //include from delimiter to end of the delimiter format excluding \n
						delimiter = temp.split(",");
						/* To Display the delimiters extracted
						 * System.out.println("Delimiter Array : ");
						for (int i =0; i<delimiter.length;i++)
							System.out.println(delimiter[i]);	*/
					}
					loop = -1;
					temp ="";
					inputString="";
				}
				}
			}
		}
		
		//Display the user input 
		/* 	System.out.println("String Entered by User is as displayed : ");
			System.out.print(inputString);
			System.out.println("Length of string : ");
			System.out.println(inputString.length()); */
		//Case 1 boolean flag = Pattern.matches("(([0-9]{1,4})?,)*([0-9]{1,4})$", inputString);
		//Case 2 boolean flag = Pattern.matches("(([0-9]{1,4})?[,\n])*([0-9]{1,4})\n$",inputString);
		//Case 3 String pattern3 = "(([0-9]{1,4})?"+delimiter[0]+")*([0-9]{1,4})\n$";
		//Case Bonus 3
		if(delimiterflag)
		{
		pattern3 = "(([0-9]{1,4})?[";
		spliterString = "[";
		for(int i=0;i<delimiter.length;i++)
		{
			tempdelimiter = "";
			for(int j=0; j<delimiter[i].length();j++)
			{
				for (int k = 0; k<specialDelimiters.length; k++)
				{
				if(delimiter[i].charAt(j) == specialDelimiters[k] )
				{
				tempdelimiter += "\\";
				break;
				}
				}
				tempdelimiter += delimiter[i].charAt(j);
			}
			delimiter[i] = tempdelimiter;
			pattern3 += delimiter[i];
			spliterString += delimiter[i];
			//System.out.println("Delimiter[ "+ i + " ]");		
			//System.out.println(delimiter[i]);
		}
		pattern3 += "])*([0-9]{1,4})\n$";
		spliterString += "\n]+";
		}
		else
		{
		pattern3 = "(([0-9]{1,4})?[,\n])*([0-9]{1,4})\n$";
		spliterString = "[,\n]+";
		}
			boolean flag = Pattern.matches(pattern3,inputString);
			//System.out.println(flag);
			String []numbers = inputString.split(spliterString);
			//System.out.println("Array of numbers separated by delimiters : ");
			//for (int i=0; i<numbers.length; i++)	
					//System.out.println(numbers[i]);
			try {			
			result = Add(numbers);
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
			System.out.println("Result : " + result);
			}

	private static int Add(String []numbers) throws Exception {
		int result = 0;
		String exceptionNumbers = "";
		int []numberstoadd = new int[10];
		boolean flag = true;
		if (numbers.length>0)
		{
			for (int i =0; i<numbers.length; i++)
			{
				numberstoadd[i] = Integer.parseInt(numbers[i]);
				if(numberstoadd[i]<0)
				{
					while (flag)
						{
						exceptionNumbers +="Negatives not Allowed.\n";
						flag = false;
						}
					exceptionNumbers += numbers[i] +" ";
				}
				else
					if (numberstoadd[i]<=1000)
							result += numberstoadd[i];
			}
			if (!exceptionNumbers.isEmpty())
				throw new Exception(exceptionNumbers);
			}
		return result;
	}
	}


