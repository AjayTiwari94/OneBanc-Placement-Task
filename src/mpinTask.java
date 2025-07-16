import java.util.*;

public class mpinTask {

    private static final Set<String> COMMONLY_USED_MPINS = new HashSet<>();

    static {
        COMMONLY_USED_MPINS.addAll(Arrays.asList(
                //for 4-digit
                "1234", "1111", "0000", "1212", "7777", "1004", "2000", "4444",
                "2222", "6969", "9999", "3333", "5555", "6666", "1122", "1313",
                //for 6-digit
                "123456", "111111", "000000", "121212", "654321", "666666",
                "112233", "101010", "222222", "696969", "999999", "123123",
                "321321", "789456", "456123", "159753", "147258", "123321",
                "111222", "110110", "314159", "202020", "200200", "010101"
        ));
    }


    public static boolean isCommonMpin(String mpin) {
        return COMMONLY_USED_MPINS.contains(mpin);
    }

    public static List<String> getWeakReasons(String mpin, String dob, String spouseDob, String anniversary) {
        List<String> reasons = new ArrayList<>();
        if (isCommonMpin(mpin)) reasons.add("COMMONLY_USED_MPIN");

        if (matchesDemographic(mpin, dob)) reasons.add("DEMOGRAPHIC_DOB_SELF");
        if (matchesDemographic(mpin, spouseDob)) reasons.add("DEMOGRAPHIC_DOB_SPOUSE");
        if (matchesDemographic(mpin, anniversary)) reasons.add("DEMOGRAPHIC_ANNIVERSARY");

        return reasons;
    }

    private static boolean matchesDemographic(String mpin, String data) {
        if (data == null) return false;

        Set<String> possiblePatterns = new HashSet<>();

        //Normal variants (substring match)
        //Part D: Mpin features check for 6 digit pins as well...
        for (int i = 0; i <= data.length() - mpin.length(); i++) {
            possiblePatterns.add(data.substring(i, i + mpin.length()));
        }

        //Adding some other formats that a customer can use (if length is 8)
        if (data.length() == 8) {
            String dd = data.substring(0, 2);
            String mm = data.substring(2, 4);
            String yyyy = data.substring(4, 8);
            String yy = yyyy.substring(2);

            possiblePatterns.add(dd + mm + yy);   // DDMMYY
            possiblePatterns.add(yy + mm + dd);   // YYMMDD
            possiblePatterns.add(mm + dd + yy);   // MMDDYY
            possiblePatterns.add(dd + mm);        // DDMM
            possiblePatterns.add(mm + dd);        // MMDD
            possiblePatterns.add(yyyy);           // YYYY
            possiblePatterns.add(dd + mm + yyyy); // Full DDMMYYYY
        }

        return possiblePatterns.contains(mpin);
    }


    public static Map<String, Object> validateMPIN(String mpin, String dob, String spouseDob, String anniversary) {
        Map<String, Object> result = new HashMap<>();
        List<String> reasons = getWeakReasons(mpin, dob, spouseDob, anniversary);
        result.put("strength", reasons.isEmpty() ? "STRONG" : "WEAK");
        result.put("reasons", reasons);
        return result;
    }

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your MPIN (4 or 6 digits): ");
        String mpin = sc.nextLine();

        System.out.print("Enter your Date of Birth (DDMMYYYY): ");
        String dob = sc.nextLine();

        System.out.print("Enter Spouse's Date of Birth (DDMMYYYY): ");
        String spouseDob = sc.nextLine();

        System.out.print("Enter Wedding Anniversary (DDMMYYYY): ");
        String anniversary = sc.nextLine();

        Map<String, Object> result = validateMPIN(mpin, dob, spouseDob, anniversary);

        System.out.println("\n===== MPIN Analysis Result =====");
        System.out.println("MPIN Strength: " + result.get("strength"));

        @SuppressWarnings("unchecked")
        List<String> reasons = (List<String>) result.get("reasons");
        if (reasons.isEmpty()) {
            System.out.println("No Weaknesses Detected.");
        } else {
            System.out.println("Reasons:");
            for (String reason : reasons) {
                System.out.println(" - " + reason);
            }
        }
    }

}

