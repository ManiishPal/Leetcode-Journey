
class Solution {
    public String reverseVowels(String s) {

        List<Character> vowels = new ArrayList<>();

        // Step 1: Collect vowels
        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                vowels.add(c);
            }
        }

        // Step 2: Reverse the vowel list
        Collections.reverse(vowels);

        // Step 3: Replace vowels
        char[] arr = s.toCharArray();
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            if (isVowel(arr[i])) {
                arr[i] = vowels.get(index++);
            }
        }

        return new String(arr);
    }

    private boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }
}
