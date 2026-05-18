class Solution {
    int emoticonPlus = 0;
    int earn = 0;
    public int[] solution(int[][] users, int[] emoticons) {
        
        int[] arr = new int[emoticons.length];
        
        comb(arr, 0, users, emoticons);
        
        int[] answer = {emoticonPlus, earn};
        return answer;
    }
    
    public void comb(int[] arr, int start, int[][] users, int[] emoticons) {
        if(start == arr.length) {
            calculate(arr, users, emoticons);
            return;
        }
        
        for(int i = 10; i <= 40; i += 10) {
            arr[start] = i;
            comb(arr, start + 1, users, emoticons);
        }
    }
    
    public void calculate(int[] arr, int[][] users, int[] emoticons) {
        int serviceCnt = 0;
        int sum = 0;
        for(int[] user: users) {
            int ratio = user[0];
            int tmpSum = 0;
            for(int i = 0 ; i < arr.length; i++) {
                if(ratio <= arr[i]) {
                    tmpSum += emoticons[i] - emoticons[i] * arr[i] / 100;
                }
            }
            
            if(tmpSum >= user[1]) {
                serviceCnt++;
            } else {
                sum += tmpSum;
            }
        }
        
        if(serviceCnt > emoticonPlus) {
            emoticonPlus = serviceCnt;
            earn = sum;
        } else if(serviceCnt == emoticonPlus && sum > earn) {
            earn = sum;
        }
        return;
    }
}