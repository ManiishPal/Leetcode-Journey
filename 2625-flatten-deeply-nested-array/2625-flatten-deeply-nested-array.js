
var flat = function (arr, n) {
    const result = [];

    function flatten(array, depth) {
        for (const element of array) {
            if (Array.isArray(element) && depth < n) {
                flatten(element, depth + 1);
            } else {
                result.push(element);
            }
        }
    }

    flatten(arr, 0);
    return result;
};
