
var cancellable = function(fn, args, t) {
    // Execute immediately
    fn(...args);

    // Execute repeatedly every t milliseconds
    const intervalId = setInterval(() => {
        fn(...args);
    }, t);

    // Return a function that stops the interval
    return function cancelFn() {
        clearInterval(intervalId);
    };
};
