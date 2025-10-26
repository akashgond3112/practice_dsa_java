package main.revision.october.meta.easy;

/**
 * यह कोड बाइनरी सर्च (binary search) का हिस्सा है, जो एक सॉर्ट किए गए ऐरे
 * (sorted array) में k-वाँ मिसिंग पॉजिटिव नंबर (k-th missing positive number)
 * ढूंढ रहा है।
 * 
 * यहाँ if-else ब्लॉक का लॉजिक इस प्रकार है:
 * 
 * arr[mid] - mid - 1: यह एक्सप्रेशन arr के mid इंडेक्स तक कितने पॉजिटिव नंबर
 * मिसिंग हैं, उसकी गिनती करता है।
 * 
 * सोचिए, अगर ऐरे में कोई भी नंबर मिसिंग नहीं होता (जैसे [1, 2, 3, 4, ...]), तो
 * किसी भी इंडेक्स i पर वैल्यू i + 1 होती (arr[i] = i + 1)।
 * तो, arr[mid] - (mid + 1) या arr[mid] - mid - 1 हमें यह बताता है कि arr[mid]
 * की वैल्यू तक पहुँचने में कितने नंबर "स्किप" या मिस हो गए हैं।
 * if (arr[mid] - mid - 1 < k): यह कंडीशन जाँचती है कि क्या mid इंडेक्स तक मिले
 * मिसिंग नंबर्स की संख्या k से कम है।
 * 
 * अगर हाँ (True): इसका मतलब है कि k-वाँ मिसिंग नंबर arr[mid] के बाद में आएगा।
 * हमें अभी तक k मिसिंग नंबर्स नहीं मिले हैं, इसलिए हमें ऐरे के दाहिने हिस्से
 * (right half) में खोजना होगा। इसीलिए हम सर्च की निचली सीमा (lower bound) को
 * left = mid + 1 पर सेट कर देते हैं।
 * अगर नहीं (False): इसका मतलब है कि mid इंडेक्स तक हमें k या उससे ज़्यादा
 * मिसिंग नंबर्स मिल चुके हैं। इसका मतलब है कि k-वाँ मिसिंग नंबर या तो arr[mid]
 * से पहले है या इसी रेंज में कहीं है। इसलिए, हम ऐरे के दाहिने हिस्से को छोड़
 * देते हैं और अपनी खोज को बाएं हिस्से तक सीमित कर देते हैं, जिसमें mid भी शामिल
 * है। ऐसा करने के लिए हम right = mid सेट करते हैं।
 * संक्षेप में, यह लॉजिक बाइनरी सर्च का उपयोग करके उस पॉइंट को ढूंढता है जहाँ
 * k-वाँ नंबर मिसिंग है, और हर स्टेप में सर्च स्पेस को आधा कर देता है।
 */
public class KthMissingPositiveNumber {

    class Solution {
        public int findKthPositive(int[] arr, int k) {

            int left = 0;
            int right = arr.length;

            while (left < right) {

                int mid = left + (right - left) / 2;
                if (arr[mid] - mid - 1 < k) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left + k;
        }
    }

    // Revision 25/10/2025
    class SolutionRevisionThirdrdDay {
        public int findKthPositive(int[] arr, int k) {

            int left = 0;
            int right = arr.length;

            while (left < right) {

                int mid = left + (right - left) / 2;

                if (arr[mid] - mid - 1 < k) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left + k;
        }
    }
}
