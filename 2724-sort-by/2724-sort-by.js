
var sortBy = function(arr, fn) {
    return arr
        .map((value) => [fn(value), value])
        .sort((a, b) => a[0] - b[0])
        .map((item) => item[1]);
};
