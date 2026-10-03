var reduce = function(nums, fn, init) {
    let results = [];
    let result = init;

    for (let i = 0; i < nums.length; i++) {
        result = fn(result, nums[i]);
        results.push(result);
    }

    return nums.length === 0 ? init : results[results.length - 1];
};