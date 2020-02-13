export class EventProvider {
    static getEvent(type) {
        return new Event(type);
    }
}