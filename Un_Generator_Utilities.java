import java.util.Scanner;
public class Un_Generator_Utilities{
	public static int gcd(int a, int b){
		if(b == 0){
			return a;
		}else{
			return gcd(b,a%b);
		}
	}
	public static String get_double_as_a_string_from_string(String input_string){
/*
	This function is rather misleading, it really only checks to see if a string contains only numbers and '-' and '.' characters. If the string doesn't, then the function returns an empty string.
*/
		for(int i = 0; i < input_string.length(); i++){
			if(!Character.isDigit(input_string.charAt(i))){
				if(!(input_string.charAt(i) == '-')){
					if(!(input_string.charAt(i) == '.')){
						return "";
					}
				}
			}
		}
		return input_string;
	}

	public static double get_double_from_user_in_range(double lower_bound, double upper_bound){
/*
	This function takes inputs for a lower bound an an upper bound, both doubles, and re-prompts the user for a double within those bounds.

	This function needs import java.util.Scanner;
	This function needs the function public static String get_double_as_a_string_from_string(String input_string)
*/
		if(upper_bound < lower_bound){
			System.out.printf("Invalid parameters in get_double_from_user_in_range. %f < %f%n", upper_bound, lower_bound);
			return 0.0;
		}
		String user_input_string;
		double output = 0.0;
		Scanner input = new Scanner(System.in);
		boolean selection = false;
		boolean valid_selection = false;
		do{
			do{
				System.out.printf("Please select a number between %f and %f: ", lower_bound, upper_bound);
				user_input_string = input.nextLine();
				user_input_string = get_double_as_a_string_from_string(user_input_string);
				if(user_input_string.length() > 0){
					selection = true;
				}else{
					System.out.printf("Invalid selection. Input does not contain only a double.%n");
				}
			}while(!selection);
			try{
				output = Double.parseDouble(user_input_string);
			}catch(NumberFormatException e){
				System.out.printf("Invalid selection. Input was not formatted as a double.%n");
				continue;
			}
			if(output <= upper_bound && output >= lower_bound){
				valid_selection = true;
			}else{
				System.out.printf("Invalid selection. Selection not in bounds.%n");
			}
		}while(!valid_selection);
		return output;
	}

	public static int get_unsighned_int_from_string(String input_string){
/*
	This function strips all non-digits from a given string and returns an integer value for the remaining digits.
*/

		String output_string = "";
		int output_int = 0;
		for(int i = 0; i < input_string.length(); i++){
			if(Character.isDigit(input_string.charAt(i))){
				output_string += input_string.charAt(i);
			}
		}
		if(output_string.length() < 1){
			output_string = "Input did not contain an integer.";
		}else{
			output_int = Integer.valueOf(output_string);
		}
		return output_int;
	}
	public static int get_unsigned_int_from_user(){
/*
	This function continues to ask the user for an integer, until the user inputs a string that contains at lease one digit.

	This function needs import java.util.Scanner;
*/
		Scanner input = new Scanner(System.in);
		String input_string;
		String output_string = "";
		do{
			input_string = input.next();
			for(int i = 0; i < input_string.length(); i++){
				if(Character.isDigit(input_string.charAt(i))){
					output_string += input_string.charAt(i);
				}
			}
			if(output_string.length() < 1){
				System.out.println("Input did not contain an integer, please input an integer: ");
			}
		}while(output_string.length() < 1);
		
		int output_int = Integer.valueOf(output_string);
		return output_int;
	}

	public static int get_user_input_of_unsignedint_from_range_and_prompt(int lower_bound, int upper_bound, String prompt){
/*
	This function gets an integer from the user between two given bounds (inclusive). If the user's input is invalid, this function reprompts the user until a valid response is given

	This function needs the function public static int get_unsigned_int_from_user()
*/
		boolean choice = false;
		int current_choice = 0;
		do{
			System.out.print(prompt+":");
			current_choice = get_unsigned_int_from_user();
			if(current_choice <= upper_bound && current_choice >= lower_bound){
				choice = true;
			}else{
				System.out.println("That wasn't a valid response.");
			}
		}while(!choice);
		return current_choice;
	}

	public static boolean get_bool_from_user_with_prompt(String prompt){
/*
	This function reprompts the user for a yes/no answer to the given prompt until a valid answer is given.

	This function needs import java.util.Scanner;
*/
		boolean output = false;
		boolean choice = false;
		String current_choice;
		Scanner input = new Scanner(System.in);
		do{
			System.out.print(prompt);
			current_choice = input.nextLine();
			if(current_choice.equalsIgnoreCase("y") || current_choice.equalsIgnoreCase("yes") || current_choice.equals("1")){
				output = true;
				choice = true;
			}else if(current_choice.equalsIgnoreCase("n") || current_choice.equalsIgnoreCase("no") || current_choice.equals("0")){
				output = false;
				choice = true;
			}else{
				System.out.println("That wasn't a valid response.");
			}
		}while(!choice);
		return output;
	}

	public static int partition(int a[], int beg, int end){
/*
	This is an object that the quicksort function uses. Totally stolen from the followint site:

	https://www.javatpoint.com/quick-sort
*/
        int left, right, temp, loc, flag;
        loc = left = beg;
        right = end;
        flag = 0;
        while(flag != 1){
            while((a[loc] <= a[right]) && (loc!=right)){
                right--;
            }
            if(loc==right){
                flag =1;
            }else if(a[loc]>a[right]){
                temp = a[loc];
                a[loc] = a[right];
                a[right] = temp;
                loc = right;
            }
            if(flag!=1){
                while((a[loc] >= a[left]) && (loc!=left)){
                    left++;
                }
                if(loc==left){
                    flag =1;
                }
                else if(a[loc] <a[left]){
                    temp = a[loc];
                    a[loc] = a[left];
                    a[left] = temp;
                    loc = left;
                }
            }
        }
        return loc;
    }
    static void quickSort(int a[], int beg, int end){
/*
	This funciton was stolen from https://www.javatpoint.com/quick-sort and is a quicksort algorithm.

	This function needs the object public static int partition(int a[], int beg, int end)
*/
        int loc;
        if(beg<end){  
            loc = partition(a, beg, end);
            quickSort(a, beg, loc-1);
            quickSort(a, loc+1, end);
        }
    }
}