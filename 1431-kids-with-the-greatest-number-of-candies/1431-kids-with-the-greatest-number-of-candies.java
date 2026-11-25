
class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

        List<Boolean> result = new ArrayList<>();

        for (int i = 0; i < candies.length; i++) {

            int newCount = candies[i] + extraCandies;
            boolean greatest = true;

            for (int j = 0; j < candies.length; j++) {

                if (candies[j] > newCount) {
                    greatest = false;
                    break;
                }
            }

            result.add(greatest);
        }

        return result;
    }
}
