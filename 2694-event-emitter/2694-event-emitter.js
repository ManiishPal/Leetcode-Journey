
class EventEmitter {
    constructor() {
        this.subscriptions = [];
    }

    subscribe(event, callback) {
        const subscription = { event, callback };
        this.subscriptions.push(subscription);

        return {
            unsubscribe: () => {
                const index = this.subscriptions.indexOf(subscription);
                if (index !== -1) {
                    this.subscriptions.splice(index, 1);
                }
            }
        };
    }

    emit(event, args = []) {
        const results = [];

        for (const subscription of this.subscriptions) {
            if (subscription.event === event) {
                results.push(subscription.callback(...args));
            }
        }

        return results;
    }
}
