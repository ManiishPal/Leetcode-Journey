
class TimeLimitedCache {
    constructor() {
        this.cache = new Map();
    }

    set(key, value, duration) {
        const now = Date.now();
        const existing = this.cache.get(key);

        const existed = existing !== undefined &&
                        existing.expiry > now;

        this.cache.set(key, {
            value: value,
            expiry: now + duration
        });

        return existed;
    }

    get(key) {
        const entry = this.cache.get(key);

        if (entry === undefined || entry.expiry <= Date.now()) {
            return -1;
        }

        return entry.value;
    }

    count() {
        let total = 0;
        const now = Date.now();

        for (const entry of this.cache.values()) {
            if (entry.expiry > now) {
                total++;
            }
        }

        return total;
    }
}
