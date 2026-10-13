# OOPS Personal Exercises

Oct 5, 2026 · @Pratyush Singh

## How to use this

Eighteen exercises: three on Classes and Objects, five on Inheritance and Polymorphism, and ten on Abstraction and Interfaces, ordered easy to hard within each topic. Every prompt is self-contained, and each exercise is its own program, so class names can repeat between exercises.

- Write each exercise in its own folder or file. No imports are needed for any of them.
- Keep every field `private` unless a prompt says otherwise. Subclasses reach parent data through getters or `super`.
- When an expected output is given, match it exactly, including spacing and punctuation.
- Answer the written questions as comments at the bottom of your file before running anything.
- Attempt first, then send your code for review. Don't look up solutions.

Concepts covered so far: classes and objects, private fields, getters and setters with validation, constructors, overloading, chaining with `this(...)`, copy constructors, shallow vs deep copy, `static` fields and methods, references vs objects, arrays of objects, `null` checks, `.equals` vs `==`, `printf`, `extends`, `super(...)` and `super.method()`, overriding and `@Override`, multilevel inheritance, and dynamic method dispatch.

## Classes and Objects

### C1. Playlist (deep copy)

Build a playlist class whose copy constructor makes a true deep copy of an array field.

**Fields**

- `private int id`
- `private String name`
- `private String[] songs` (fixed-size array of song titles)
- `private int songCount` (how many slots are filled)
- `private static int nextId` starting at 1. Each new playlist takes the current value as its `id`, then `nextId` goes up by 1.

**Constructors**

1. `Playlist(String name, int capacity)`: the main constructor. Creates the array and assigns the id. If `capacity` is less than 1, use 5.
2. `Playlist(String name)`: capacity 5. Must chain with `this(...)`.
3. `Playlist(Playlist other)`: a deep copy. The copy gets its own new array holding the same titles, the same `songCount`, a new id, and the name `"Copy of " + other.name`. Chaining is allowed but not required.

**Methods**

- `void addSong(String title)`: adds to the next empty slot. If full, print `Playlist full` and add nothing.
- `int getId()` and `int getSongCount()`
- A method that replaces the song at a 1-based position (position 1 is the first song). You design its name, return type and what happens for a bad position, such as 0, a negative number, or a position past `songCount`.
- `void display()`: prints the header, then one numbered line per song.

**Main**

1. Create `"Gym"` with capacity 3. Add `Song A`, `Song B`, `Song C`, `Song D`. The fourth prints `Playlist full`.
2. Create `"Study"` with constructor 2. Add `Lofi 1` and `Lofi 2`.
3. Copy the Gym playlist. In the copy, replace position 2 with `Song X`. Then try replacing position 7 and show your bad-position behavior.
4. Display all three playlists.

Expected output (plus whatever your bad-position case prints):

```
Playlist full
Playlist #1: Gym (3/3)
1. Song A
2. Song B
3. Song C
Playlist #2: Study (2/5)
1. Lofi 1
2. Lofi 2
Playlist #3: Copy of Gym (3/3)
1. Song A
2. Song X
3. Song C
```

**Written question:** if the copy constructor had done `this.songs = other.songs;` instead of making a new array, what would the Gym playlist show after step 3, and why?

### C2. Store inventory

Model a small store's stock system: products with validated data, and an inventory that manages an array of them.

**Class `Product`**

- Fields: `sku` (String), `name` (String), `price` (double), `quantity` (int).
- Constructor `Product(String sku, String name, double price, int quantity)`. Set the price by calling `setPrice`, so a negative price is rejected there. If `quantity` is negative, use 0.
- Getters for all four fields.
- `void setPrice(double price)`: if negative, print `Invalid price` and keep the old value. Otherwise update it.
- `void restock(int amount)`: if `amount` is 0 or less, print `Invalid amount`. Otherwise add it to `quantity`.
- `boolean sell(int amount)`: returns `false` without changing anything if `amount` is 0 or less, or greater than `quantity`. Otherwise subtracts and returns `true`. Prints nothing.
- `double getStockValue()`: price times quantity.
- `void display()`: prints `SKU | name | $price | qty N` with price to 2 decimals, for example `SKU-102 | Pen | $1.25 | qty 3`.

**Class `Inventory`**

- Fields: `private Product[] products` and `private int size`.
- `Inventory(int capacity)`: if `capacity` is less than 1, use 10. `Inventory()`: capacity 10, chained.
- `boolean addProduct(Product p)`: return `false` for `null`. Check for a duplicate SKU first (compare with `.equals`) and print `Duplicate SKU: <sku>`. Then check capacity and print `Inventory full`. Otherwise add it and return `true`.
- `Product findBySku(String sku)`: returns the matching product, or `null` if none.
- `boolean sell(String sku, int amount)`: if the SKU isn't found, print `Unknown SKU: <sku>`. If the product's `sell` fails, print `Sale failed: <sku>`. On success, print `Sold <amount> x <name>`.
- `void printLowStock(int threshold)`: prints `Low stock (below N):`, then `display()` for every product with quantity below the threshold, in insertion order. If there are none, print `none`.
- `double getTotalValue()`: the sum of every product's stock value.

**Main** (in this order)

1. Create `Product("SKU-101", "Notebook", 3.50, 40)`, `Product("SKU-102", "Pen", 1.25, 8)`, `Product("SKU-103", "Backpack", -20.0, 2)`, `Product("SKU-101", "Fake Notebook", 1.00, 5)` and `Product("SKU-104", "Stapler", 6.00, 3)`.
2. Fix the backpack with `setPrice(29.99)`.
3. Create `new Inventory(3)`. Add the five products in the order created.
4. Sell 5 of `SKU-102`, then 5 more of `SKU-102`, then 1 of `SKU-999`.
5. Call `printLowStock(5)`.
6. Get the backpack with `findBySku("SKU-103")` and restock it by 10.
7. Call `printLowStock(5)` again.
8. Print `Total inventory value: $` followed by the total to 2 decimals.

Expected output:

```
Invalid price
Duplicate SKU: SKU-101
Inventory full
Sold 5 x Pen
Sale failed: SKU-102
Unknown SKU: SKU-999
Low stock (below 5):
SKU-102 | Pen | $1.25 | qty 3
SKU-103 | Backpack | $29.99 | qty 2
Low stock (below 5):
SKU-102 | Pen | $1.25 | qty 3
Total inventory value: $503.63
```

**Written questions:**

1. Step 6 restocks a product obtained from `findBySku`, yet the inventory sees the change. Why?
2. Suppose `Inventory` had `public Product[] getProducts() { return products; }`. What could code in `main` break with it, and how would you prevent that?

### C3. Library loans

Two classes that hold references to each other's data: books track copies, members track what they've borrowed.

**Class `Book`**

- Fields: `isbn`, `title`, `author` (all String), `totalCopies` and `availableCopies` (int).
- `Book(String isbn, String title, String author, int copies)`: if `copies` is less than 1, use 1. Available starts equal to total.
- `Book(String isbn, String title, String author)`: one copy, chained.
- Getters for `isbn` and `title`.
- `boolean checkOut()`: if a copy is available, decrease available and return `true`. Otherwise return `false`.
- `void checkIn()`: if available is already equal to total, print `All copies already in for <title>`. Otherwise increase available.
- `void display()`: prints the title in double quotes, then `by <author> (<available>/<total> available)`, for example `"Clean Code" by Robert Martin (0/2 available)`.

**Class `Member`**

- Fields: `memberId` (int), `name` (String), `Book[] borrowed` of length 3, `borrowedCount` (int), and `private static int nextMemberId` starting at 1001.
- `Member(String name)`: assigns the next id.
- `boolean borrow(Book b)`, with checks in this exact order:
  1. If the member already has 3 books, print `<name> has reached the limit`.
  2. If they already hold a book with the same ISBN (use `.equals`), print `<name> already has <title>`.
  3. If `b.checkOut()` returns false, print `No copies of <title> available`.
  4. Otherwise store the book, print `<name> borrowed <title>` and return `true`. Return `false` in the three failure cases.
- `boolean returnBook(String isbn)`: if not found, print `<name> does not have <isbn>`. If found, call `checkIn()` on it, remove it from the array by shifting later books left so there are no gaps, and print `<name> returned <title>`.
- `void displayBorrowed()`: prints `Member <id> (<name>): <count> book(s)`, then one line per book: two spaces, a dash, a space, then the title.

**Main** (in this order)

1. Books: `Book("111", "Clean Code", "Robert Martin", 2)`, `Book("222", "Effective Java", "Joshua Bloch")`, `Book("333", "Refactoring", "Martin Fowler", 0)` and `Book("444", "The Pragmatic Programmer", "Hunt and Thomas", 3)`. Members: Asha, then Ravi.
2. Asha borrows Clean Code, then Effective Java. Ravi tries Effective Java. Asha tries Clean Code again. Asha borrows Refactoring, then tries The Pragmatic Programmer. Ravi borrows Clean Code.
3. Display Clean Code.
4. Asha returns `"222"`. Ravi borrows Effective Java. Asha tries to return `"999"`.
5. Display both members' borrowed lists.
6. Call `checkIn()` on The Pragmatic Programmer.

Expected output:

```
Asha borrowed Clean Code
Asha borrowed Effective Java
No copies of Effective Java available
Asha already has Clean Code
Asha borrowed Refactoring
Asha has reached the limit
Ravi borrowed Clean Code
"Clean Code" by Robert Martin (0/2 available)
Asha returned Effective Java
Ravi borrowed Effective Java
Asha does not have 999
Member 1001 (Asha): 2 book(s)
  - Clean Code
  - Refactoring
Member 1002 (Ravi): 2 book(s)
  - Clean Code
  - Effective Java
All copies already in for The Pragmatic Programmer
```

**Written questions:**

1. Asha and Ravi are separate objects, yet Ravi's borrow changes what Clean Code shows. Why?
2. Why must the "already has" check run before `checkOut()`? What bug appears if you swap them?

## Inheritance and Polymorphism

### I1. Vehicles (easy)

Practice `extends`, `super(...)`, private parent fields and calling the parent's version of an overridden method.

**Class `Vehicle`**

- Private fields `brand` (String) and `year` (int), a constructor `Vehicle(String brand, int year)`, and getters for both.
- `void describe()`: prints `<year> <brand>`, for example `2019 Honda`.

**Class `Car extends Vehicle`**

- Private field `numDoors` (int). Constructor `Car(String brand, int year, int numDoors)` that passes brand and year up with `super(...)`.
- Override `describe()` with `@Override`: call `super.describe()`, then print two spaces followed by `Doors: <numDoors>`.

**Class `Motorcycle extends Vehicle`**

- Private field `hasSidecar` (boolean), set through its constructor the same way.
- Override `describe()`: call `super.describe()`, then print two spaces followed by `Sidecar: yes` or `Sidecar: no`.

**Main:** create `Car("Honda", 2019, 4)`, `Motorcycle("Harley-Davidson", 2021, false)` and `Vehicle("Ford", 2015)`, then call `describe()` on each.

```
2019 Honda
  Doors: 4
2021 Harley-Davidson
  Sidecar: no
2015 Ford
```

**Experiments** (answer in comments, then undo):

1. Add `System.out.println("Vehicle constructor");` to Vehicle's constructor and a matching line to Car's. Predict the order they print when you create one Car, then check.
2. Delete the `super(...)` line from Car's constructor. What compile error appears, and why does Java need it here?
3. Try printing `brand` directly inside Car's `describe()`. Why doesn't it compile, and what's the fix that keeps the field private?

### I2. Payroll (dynamic dispatch)

One payslip method, written once in the parent, prints the right pay for every kind of employee.

**Class `Employee`**

- Private fields `name` (String) and `annualSalary` (double), a constructor, and getters.
- `double monthlyPay()`: returns `annualSalary / 12`.
- `double payFor(int months)`: returns `monthlyPay() * months`. Do not override this in any subclass.
- `String getRole()`: returns `"Employee"`.
- `void printPayslip()`: prints `<name> (<role>): $<monthly pay>/month`, with pay to 2 decimals. Use `getRole()` and `monthlyPay()`. Do not override this in any subclass.

**Class `Manager extends Employee`**

- Private field `annualBonus` (double) with a getter. Constructor `Manager(String name, double annualSalary, double annualBonus)`.
- Override `monthlyPay()` to return `super.monthlyPay() + annualBonus / 12`. Override `getRole()` to return `"Manager"`.

**Class `Intern extends Employee`**

- Private fields `hourlyRate` (double) and `hoursPerMonth` (int). Constructor `Intern(String name, double hourlyRate, int hoursPerMonth)` that passes an annual salary of 0 to the parent.
- Override `monthlyPay()` to return `hourlyRate * hoursPerMonth`. Override `getRole()` to return `"Intern"`.

**Main**

1. Make an `Employee[] staff` holding `Employee("Asha", 72000)`, `Manager("Ravi", 96000, 24000)` and `Intern("Leo", 25.0, 80)`.
2. Loop over the array and call `printPayslip()` on each.
3. Loop again, add up `payFor(3)` for everyone, and print `Total for 3 months: $` followed by the total to 2 decimals.

```
Asha (Employee): $6000.00/month
Ravi (Manager): $10000.00/month
Leo (Intern): $2000.00/month
Total for 3 months: $54000.00
```

**Written questions:**

1. `printPayslip()` and `payFor()` exist only in `Employee`, yet they print the manager's and intern's correct pay. Explain exactly how Java decides which `monthlyPay()` to run.
2. Can you call `staff[1].getAnnualBonus()` in main? Why or why not, given the object really is a Manager?
3. Is `payFor(int months)` overloading or overriding `monthlyPay()`? What's the difference between the two?

### I3. Media library (overriding toString and equals)

Every class secretly extends `Object`, which already has `public String toString()` and `public boolean equals(Object other)`. Override both to give media items real text and real equality.

**Class `Media`**

- Private fields `title` (String) and `year` (int), a constructor, and getters.
- Override `toString()` to return `<title> (<year>)`, for example `Inception (2010)`. When you pass an object to `println`, Java calls `toString()` automatically.
- Override `equals(Object other)` using this standard pattern:
  1. If `this == other`, return `true`.
  2. If `other == null` or `getClass() != other.getClass()`, return `false`. (`getClass()` gives the object's real runtime class, so a Movie never equals a Song.)
  3. Cast with `Media m = (Media) other;`, then return whether the titles match (using `.equals`) and the years match.

**Class `Movie extends Media`**

- Private field `minutes` (int).
- `toString()` returns `super.toString()` followed by `  [movie, <minutes> min] `.
- `equals(Object other)`: return `false` if `super.equals(other)` is false. Otherwise cast to `Movie` and compare `minutes`.

**Class `Song extends Media`**

- Private field `artist` (String).
- `toString()` returns `super.toString()` followed by `  [song by <artist>] `.
- `equals(Object other)`: same idea as Movie, comparing `artist` with `.equals`.

**Static helper in your main class:** `static int countCopies(Media[] library, Media target)` returns how many items in `library` are equal to `target` according to `equals`.

**Main**

1. Create `m1 = new Movie("Inception", 2010, 148)`, `m2 = new Movie("Inception", 2010, 148)`, `m3 = new Movie("Inception", 2010, 150)` and `s1 = new Song("Inception", 2010, "Hans Zimmer")`.
2. Print `m1`, then `s1`.
3. Print, each on its own line: `m1 == m2`, `m1.equals(m2)`, `m1.equals(m3)`, `m1.equals(s1)`, `m1.equals(null)`.
4. Build `Media[] library = {m1, m2, m3, s1, new Song("Inception", 2010, "Hans Zimmer")};` and print `countCopies(library, m1)`, then `countCopies(library, s1)`.

```
Inception (2010) [movie, 148 min]
Inception (2010) [song by Hans Zimmer]
false
true
false
false
false
2
2
```

**Written question:** change Movie's method header to `public boolean equals(Movie other)` and run `countCopies(library, m1)` again. Predict the new number first. Why did it change, and what does that say about overloading vs overriding?

### I4. Notification service (multilevel inheritance)

A backend sends notifications over different channels. Each channel validates and formats differently, but one `send()` method in the parent handles them all.

**Class `Notification`**

- Private fields `recipient` and `message` (String), a constructor, and getters.
- `private static int totalSent` with a static getter.
- `boolean canSend()`: `true` if `recipient` is not null and not empty, and `message` is not empty.
- `String channel()`: returns `"GENERIC"`.
- `String format()`: returns `To <recipient>: <message>`.
- `boolean send()`: if `canSend()` is false, print `FAILED [<channel>] to <recipient>` and return `false`. Otherwise print `[<channel>] <format()>`, increase `totalSent`, and return `true`. Do not override `send()` in any subclass.

**Class `EmailNotification extends Notification`**

- Private field `subject`. Constructor `EmailNotification(String recipient, String subject, String message)`.
- `canSend()`: the parent's checks pass and the recipient contains `"@"`.
- `channel()`: `"EMAIL"`.
- `format()`: `To <recipient> | Subject: <subject> | <message>`.

**Class `SMSNotification extends Notification`**

- `canSend()`: the parent's checks pass and the recipient is exactly 10 characters, all digits. Loop over it with `charAt` and `Character.isDigit`.
- `channel()`: `"SMS"`.
- `format()`: like the parent's, but if the message is longer than 20 characters, use only its first 17 characters followed by `...`.

**Class `UrgentEmailNotification extends EmailNotification`**

- Constructor with the same three parameters, passed up with `super(...)`.
- `channel()`: `"EMAIL-URGENT"`.
- `format()`: ` !!!  ` followed by the email version of the format. Get it with `super.format()`, don't rebuild it.

**Main**

1. Build a `Notification[] queue` in this order:
   - `EmailNotification("asha@ucsd.edu", "PA1", "Due Thursday 11:59pm")`
   - `SMSNotification("8585551234", "Your table is ready, please come to the front desk")`
   - `EmailNotification("not-an-email", "Hi", "Test")`
   - `SMSNotification("555-1234", "Ping")`
   - `UrgentEmailNotification("ravi@ucsd.edu", "Server down", "Prod is returning 500s")`
   - `Notification("ops-team", "Weekly report ready")`
2. Call `send()` on each, counting the successes.
3. Print `Sent <successes> of <queue length>`, then `Total sent (static): <totalSent>`.

```
[EMAIL] To asha@ucsd.edu | Subject: PA1 | Due Thursday 11:59pm
[SMS] To 8585551234: Your table is rea...
FAILED [EMAIL] to not-an-email
FAILED [SMS] to 555-1234
[EMAIL-URGENT] !!! To ravi@ucsd.edu | Subject: Server down | Prod is returning 500s
[GENERIC] To ops-team: Weekly report ready
Sent 4 of 6
Total sent (static): 4
```

**Written questions:**

1. `send()` is written once, yet it prints four different channels and formats. Trace exactly which `channel()` and `format()` run for the urgent email.
2. Why do the subclasses call `super.canSend()` instead of copying the parent's checks?
3. Why is `totalSent` static rather than an instance field?

### I5. Parking garage (system design)

A classic interview design problem. Vehicles of different sizes park in spots of different sizes, get tickets and pay by the hour. Everything from both topics shows up here.

**Vehicles**

- `Vehicle`: private `plate` (String), constructor, getter. Methods `int getSize()` returns 2, `double hourlyRate()` returns 3.00, `String getType()` returns `"Vehicle"`.
- `Motorcycle extends Vehicle`: size 1, rate 1.50, type `"Motorcycle"`.
- `Car extends Vehicle`: overrides only `getType()` to return `"Car"`.
- `ElectricCar extends Car`: type `"Electric Car"`. Its rate is the car rate minus 1.00, written with `super.hourlyRate()`, not a hardcoded 2.00.
- `Truck extends Vehicle`: size 3, rate 6.00, type `"Truck"`.

**Class `ParkingSpot`**

- Private `number` (int), `size` (int: 1 = S, 2 = M, 3 = L) and `parked` (Vehicle, `null` when empty).
- `boolean canFit(Vehicle v)`: the spot is empty and `v.getSize()` is at most the spot's size.
- `void park(Vehicle v)`, `void leave()`, and getters.

**Class `Ticket`**

- Private `ticketNumber`, `spotNumber`, `entryHour` (int) and `vehicle` (Vehicle), plus getters.
- `private static int nextTicket` starting at 1.
- `Ticket(Vehicle vehicle, int spotNumber, int entryHour)`: takes the next ticket number.
- `Ticket(Ticket other)`: a reprint of a lost ticket. It keeps the same ticket number and data, and must not change `nextTicket`.

**Class `ParkingLot`**

- Private `ParkingSpot[] spots`, `Ticket[] activeTickets` (same length as `spots`, where index i holds the ticket for spot i) and `revenue` (double).
- `ParkingLot(int[] spotSizes)`: creates one spot per entry, numbered from 1.
- `Ticket enter(Vehicle v, int hour)`:
  1. If a vehicle with the same plate is already parked (`.equals`), print `Already parked: <plate>` and return `null`.
  2. Pick the spot with the smallest size that fits. Break ties by lowest spot number.
  3. If none fits, print `No spot for <type> <plate>` and return `null`. No ticket is created.
  4. Otherwise park, create and store the ticket, print `Parked <type> <plate> at spot <n> (ticket #<t>)` and return it.
- `double exit(int ticketNumber, int hour)`:
  1. If no active ticket has that number, print `Invalid ticket #<t>` and return 0.
  2. Hours = exit hour minus entry hour, with a minimum of 1. Fee = hours × the vehicle's hourly rate, capped at 25.00.
  3. Free the spot, remove the ticket, add the fee to revenue, print `<plate> paid $<fee> for <hours> hour(s)` with the fee to 2 decimals, and return the fee.
- `void printStatus()`: one line per spot, `Spot <n> [<S/M/L>]: empty` or `Spot <n> [<S/M/L>]: <type> <plate>`.
- `double getRevenue()`.

**Main** (in this order)

1. `ParkingLot lot = new ParkingLot(new int[]{1, 2, 2, 3});`
2. Vehicles: `Motorcycle("MOTO1")`, `Car("CAR111")`, `ElectricCar("EV222")`, `Truck("TRK333")`, `Car("CAR444")`.
3. Enter MOTO1 at hour 8, CAR111 at 9, EV222 at 9, TRK333 at 10, CAR444 at 10, then CAR111 again at 11.
4. `printStatus()`.
5. EV222's ticket is lost. Make a reprint with the copy constructor from the original ticket and print `Reprinted ticket #` followed by its number.
6. Exit using the reprint's number at hour 13. Exit ticket 3 again at hour 14.
7. Enter CAR444 at hour 14.
8. Exit ticket 1 at hour 8, ticket 4 at hour 15, ticket 2 at hour 12.
9. `printStatus()`, then print `Total revenue: $` followed by revenue to 2 decimals.

```
Parked Motorcycle MOTO1 at spot 1 (ticket #1)
Parked Car CAR111 at spot 2 (ticket #2)
Parked Electric Car EV222 at spot 3 (ticket #3)
Parked Truck TRK333 at spot 4 (ticket #4)
No spot for Car CAR444
Already parked: CAR111
Spot 1 [S]: Motorcycle MOTO1
Spot 2 [M]: Car CAR111
Spot 3 [M]: Electric Car EV222
Spot 4 [L]: Truck TRK333
Reprinted ticket #3
EV222 paid $8.00 for 4 hour(s)
Invalid ticket #3
Parked Car CAR444 at spot 3 (ticket #5)
MOTO1 paid $1.50 for 1 hour(s)
TRK333 paid $25.00 for 5 hour(s)
CAR111 paid $9.00 for 3 hour(s)
Spot 1 [S]: empty
Spot 2 [M]: empty
Spot 3 [M]: Car CAR444
Spot 4 [L]: empty
Total revenue: $43.50
```

**Written questions:**

1. Why can't the `Ticket` copy constructor chain to the main constructor with `this(...)`?
2. Point to every line in `exit()` and `enter()` where dynamic dispatch decides the result.
3. A `Bus` (size 4, rate 10.00) is added next month. Which existing classes would you have to change? What does that tell you about this design?

## Abstraction and Interfaces

These ten use abstract classes and interfaces together with everything from the first two topics. Arrays only: no `ArrayList`, generics or exceptions. Two small tools appear that your checklist covers in this section: `instanceof` (checks whether an object is of a type, including an interface) and `default` methods (interface methods with a body).

### A1. Shapes (easy)

An abstract class that declares what every shape must do, with subclasses filling in how.

**Abstract class `Shape`**

- Private field `name`, a constructor `Shape(String name)`, and a getter.
- `abstract double area();` and `abstract double perimeter();`
- `void describe()`: prints `<name>: area=<area>, perimeter=<perimeter>`, both to 2 decimals.

**Subclasses**

- `Circle(double radius)`: name `"Circle"`. Use `Math.PI`.
- `Rectangle(double width, double height)`: name `"Rectangle"`.
- `Square(double side)`: name `"Square"`. Extend `Shape` directly.

**Main**

1. Build `Shape[] shapes = {new Circle(1.0), new Rectangle(2, 3), new Square(4)};` and call `describe()` on each.
2. Loop to find the shape with the largest area and print `Largest: <name>`.

```
Circle: area=3.14, perimeter=6.28
Rectangle: area=6.00, perimeter=10.00
Square: area=16.00, perimeter=16.00
Largest: Square
```

**Written questions:**

1. `describe()` calls `area()`, but `Shape` never implements `area()`. Why does it still work?
2. Try `new Shape("Blob")`. What error do you get, and why is that a good thing here?
3. Remove `perimeter()` from `Square`. What does the compiler say?

### A2. Devices (easy)

Two unrelated capabilities as interfaces, and one class that has both.

**Interfaces**

- `Playable`: `void play();`
- `Rechargeable`: `int getBattery();` and `void charge(int minutes);`

**Classes**

- `Speaker implements Playable, Rechargeable`. Battery starts at 50.
  - `play()`: if battery is below 10, print `Speaker battery too low`. Otherwise subtract 10, then print `Speaker playing (battery <battery>%)`.
  - `charge(minutes)`: adds 2 per minute, capped at 100.
- `Piano implements Playable`: `play()` prints `Piano: plink plonk`.
- `Phone implements Rechargeable`: battery starts at 20, same charging rule as Speaker.

**Main**

1. Create one `Speaker s`. Build `Playable[] players = {s, new Piano()};` and `Rechargeable[] chargeables = {s, new Phone()};`.
2. Call `play()` on every item in `players`.
3. Charge every item in `chargeables` for 30 minutes, then print `Battery: <battery>%` for each.
4. Call `players[0].play()` once more.

```
Speaker playing (battery 40%)
Piano: plink plonk
Battery: 100%
Battery: 80%
Speaker playing (battery 90%)
```

**Written questions:**

1. The same Speaker object sits in two arrays of different types. How is that allowed?
2. Can you call `players[0].charge(10)`? Why not, even though the object really is a Speaker?
3. What does a brand-new Speaker print on its sixth `play()`?

### A3. Payments (easy to medium)

An abstract class for what all payment methods share, and an interface for what only some can do.

**Interface `Refundable`**

- `void refund(double amount);`
- `default String refundPolicy()` that returns `"Refunds within 30 days"`.

**Abstract class `PaymentMethod`**

- Private field `owner` with a getter, and `private static int transactions` with a static getter.
- `abstract double fee(double amount);` and `abstract String type();`
- `boolean pay(double amount)`: if `amount` is 0 or less, print `Invalid amount` and return `false`. Otherwise print `<owner> paid $<amount + fee> (fee $<fee>) via <type>` with money to 2 decimals, add 1 to `transactions`, and return `true`.

**Subclasses**

- `CreditCard extends PaymentMethod implements Refundable`: fee is 3% of the amount, type `"Credit Card"`. `refund` prints `Refunded $<amount> to card`. Overrides `refundPolicy()` to return `"Refunds within 60 days"`.
- `Cash extends PaymentMethod`: no fee, type `"Cash"`. Not refundable.
- `GiftCard extends PaymentMethod implements Refundable`: private `balance` set by the constructor, no fee, type `"Gift Card"`. Override `pay`: if the amount is more than the balance, print `Insufficient gift card balance` and return `false`. Otherwise call `super.pay(amount)`, and only if that returns `true`, subtract from the balance. `refund` adds to the balance and prints `Refunded $<amount> to gift card (balance $<balance>)`. Keeps the default policy.

**Main**

1. `PaymentMethod[] methods = {new CreditCard("Asha"), new Cash("Ravi"), new GiftCard("Leo", 50.0)};`
2. Call `pay(40.0)` on each. Then call `pay(40.0)` on the gift card again, and `pay(0)` on the cash.
3. Loop over `methods`. If an item is `instanceof Refundable`, cast it with `Refundable r = (Refundable) methods[i];`, print its `refundPolicy()`, then call `r.refund(10.0)`. Otherwise print `<owner>: not refundable`.
4. Print `Transactions: <count>`.

```
Asha paid $41.20 (fee $1.20) via Credit Card
Ravi paid $40.00 (fee $0.00) via Cash
Leo paid $40.00 (fee $0.00) via Gift Card
Insufficient gift card balance
Invalid amount
Refunds within 60 days
Refunded $10.00 to card
Ravi: not refundable
Refunds within 30 days
Refunded $10.00 to gift card (balance $20.00)
Transactions: 3
```

**Written questions:**

1. Why is `PaymentMethod` an abstract class while `Refundable` is an interface? Could you swap them?
2. Why couldn't `Refundable` hold the `owner` field?
3. GiftCard uses the default `refundPolicy()` without writing it. What would happen if two interfaces it implemented both had a default method with the same name?

### A4. Priority queue of work (medium)

An interface lets unrelated classes be sorted together by one method that knows nothing about them.

**Interface `Prioritized`**

- `int getPriority();` (1 is most urgent), `int getDueDay();`, `String getTitle();`
- `default String tag()` returning `"[P" + getPriority() + "]"`.

**Abstract class `WorkItem implements Prioritized`**

- Private `title`, `priority`, `dueDay`, a constructor, and the three getters.
- `abstract String kind();`
- Override `toString()` to return `<tag()> <kind()>: <title> (day <dueDay>)`.

**Subclasses of `WorkItem`**

- `Task`: kind `"Task"`.
- `Meeting`: kind `"Meeting"`.
- `Bug`: kind `"Bug"`, plus a private boolean `critical` from its constructor `Bug(String title, int priority, int dueDay, boolean critical)`. Override `getPriority()`: critical bugs always return 1, others return the normal priority.

**Class `Reminder implements Prioritized`** (not a WorkItem)

- Private `text` and `day`. Priority is always 5.
- `toString()` returns `<tag()> Reminder: <text> (day <day>)`.

**Class `Sorter`**

- `static boolean comesBefore(Prioritized a, Prioritized b)`: lower priority number first. On a tie, earlier due day first. On another tie, title in alphabetical order using `String`'s `compareTo`.
- `static void sort(Prioritized[] items)`: a selection sort that uses `comesBefore`. Write it yourself.

**Main**

1. Build a `Prioritized[]` with, in this order: `Task("Write PA1", 1, 8)`, `Bug("Login crash", 2, 6, true)`, `Meeting("Standup", 3, 7)`, `Task("Read zyBooks", 2, 9)`, `Bug("Typo on page", 3, 7, false)`, `Reminder("Call mom", 4)`, `Task("Email TA", 2, 9)`.
2. Sort it and print every item.
3. Print `Due by day 7: <count>`.

```
[P1] Bug: Login crash (day 6)
[P1] Task: Write PA1 (day 8)
[P2] Task: Email TA (day 9)
[P2] Task: Read zyBooks (day 9)
[P3] Meeting: Standup (day 7)
[P3] Bug: Typo on page (day 7)
[P5] Reminder: Call mom (day 4)
Due by day 7: 4
```

**Written questions:**

1. `Reminder` isn't a `WorkItem`, yet it sorts alongside them. What makes that possible?
2. The Login crash bug shows `[P1]` even though it was created with priority 2. Trace exactly how `tag()` ends up calling Bug's `getPriority()`.
3. `WorkItem` implements `Prioritized` but never defines `kind()` itself. Why does that compile?

### A5. Report exporter (medium)

The template method pattern: the parent fixes the steps of an algorithm, and subclasses fill in each step.

**Abstract class `Exporter`**

- Private `title` with a getter, and `private static int exportCount` with a static getter.
- `abstract String header();` and `abstract String row(String[] cells, int index);` (index 0 is the column-names row).
- `String footer(int rowCount)`: returns `""` by default. Subclasses may override it.
- `static String join(String[] cells, String separator)`: joins the cells with the separator between them. Write the loop yourself.
- `public final void export(String[][] data)`: prints `header()`, then `row(...)` for every row, then `footer(data.length)` only if it isn't empty, then adds 1 to `exportCount`. `final` means no subclass can override this method.

**Subclasses**

- `CsvExporter`: header `# <title>`. Each row is the cells joined by `,`. No footer.
- `MarkdownExporter`: header `## <title>`. Each row is ` |  ` + cells joined by `|` + `  | `. Row 0 is followed on the next line by a separator with one `---` per column, for example `| --- | --- |`. Footer: `_<rowCount - 1> data rows_`.
- `PlainTextExporter`: header is the title in uppercase, then a new line with one `=` per character of the title. Each row is the cells joined by `|`. Footer: `Total rows: <rowCount>`.

**Main**

1. `String[][] data = {{"Name", "Score"}, {"Asha", "92"}, {"Ravi", "85"}};`
2. Build an `Exporter[]` with one of each subclass, all titled `"Grades"`, in the order above, and call `export(data)` on each.
3. Print `Exports: <count>`.

```
# Grades
Name,Score
Asha,92
Ravi,85
## Grades
| Name | Score |
| --- | --- |
| Asha | 92 |
| Ravi | 85 |
_2 data rows_
GRADES
======
Name | Score
Asha | 92
Ravi | 85
Total rows: 3
Exports: 3
```

**Written questions:**

1. Try overriding `export` in a subclass. What happens, and why would a library author want that?
2. `header()` is abstract, but `footer()` has a default. When would you choose each?

### A6. Smart home hub (medium to hard)

Interfaces that extend other interfaces, an abstract class that implements the shared part, and a hub that treats every device the same.

**Interfaces**

- `Device`: `String getName();`, `boolean isOn();`, `void turnOn();`, `void turnOff();`, `String status();`
- `Dimmable extends Device`: `void setBrightness(int percent);` and `int getBrightness();`
- `Schedulable`: `void schedule(int hour, boolean turnOn);` and `void tick(int hour);`

**Abstract class `AbstractDevice implements Device`**

- Private `name` and `on`, a constructor, and implementations of `getName`, `isOn`, `turnOn`, `turnOff`.
- `status()` returns `<name>: ON` or `<name>: OFF`.

**Devices**

- `SmartLight extends AbstractDevice implements Dimmable, Schedulable`: brightness starts at 0. `setBrightness` clamps to 0–100, turns the light on if the result is above 0 and off if it is 0. `turnOff()` does not reset brightness. `status()` adds `  (<brightness>%) ` after `super.status()` when on.
- `SmartPlug extends AbstractDevice implements Schedulable`.
- `Thermostat extends AbstractDevice`: private `target` set in the constructor. `setTarget(int t)`: if `t` is outside 60–80, print `Target out of range` and keep the old value. `status()` adds `  (<target>F) ` when on.
- Scheduling (for both Schedulable devices): each device stores one scheduled action, an hour plus on or off. `tick(hour)`: if it matches, perform it and print `<name> turned on by schedule` or `<name> turned off by schedule`.

**Class `Hub`**

- Private `Device[] devices` of capacity 10 and a count. `addDevice(Device d)`.
- `printStatus()`: prints `status()` for every device.
- `runHour(int hour)`: calls `tick(hour)` on every device that is `instanceof Schedulable`.
- `allOff()`: turns every device off, then prints `All devices off`.
- `int countOn()`.

**Main** (in this order)

1. Create `SmartLight("Desk Lamp")`, `SmartPlug("Coffee Maker")` and `Thermostat("Thermostat", 68)`, and add all three to a hub.
2. Set the lamp's brightness to 70. Turn the thermostat on, set its target to 72, then to 95.
3. Schedule the plug to turn on at hour 7 and the lamp to turn off at hour 23.
4. `printStatus()`, `runHour(7)`, `runHour(23)`, `printStatus()`.
5. Set the lamp's brightness to 150. Call `allOff()`. Print the lamp's brightness, then `Devices on: <countOn()>`.

```
Target out of range
Desk Lamp: ON (70%)
Coffee Maker: OFF
Thermostat: ON (72F)
Coffee Maker turned on by schedule
Desk Lamp turned off by schedule
Desk Lamp: OFF
Coffee Maker: ON
Thermostat: ON (72F)
All devices off
100
Devices on: 0
```

**Written questions:**

1. `SmartLight` never writes `turnOn()`, yet it satisfies `Dimmable`, which extends `Device`. Where does that method come from?
2. Why does `runHour` need `instanceof`, but `printStatus` doesn't?
3. What would be lost if `Device` were an abstract class instead of an interface?

### A7. Ride-share dispatcher (hard)

The strategy pattern: pricing rules are objects you can swap at runtime, without touching the dispatcher.

**Vehicles**

- `abstract class Vehicle`: private `plate` with a getter, plus `abstract int capacity();`, `abstract double ratePerKm();` and `abstract String type();`
- `Sedan`: capacity 4, rate 1.50, type `"Sedan"`. `XL`: capacity 6, rate 2.25, type `"XL"`.

**Class `Driver`**

- Private `name`, `vehicle`, `x`, `y` (ints), `rating` (double) and `available` (starts `true`), with getters, `setAvailable(boolean)` and `moveTo(int x, int y)`.

**Interface `PricingStrategy`**

- `double fare(int km, Vehicle v);` and `String name();`
- `StandardPricing`: `km × ratePerKm + 2.00`, with a minimum of 5.00. Name `"Standard"`.
- `SurgePricing`: constructor takes a `double multiplier` and keeps a `StandardPricing` object inside. Fare is the standard fare times the multiplier. Name `"Surge x" + multiplier`.

**Class `Ride`**

- Holds the rider name, driver, km, fare and pricing name.
- `toString()` returns `<rider> -> <driver> (<type> <plate>), <km> km, $<fare> [<pricing name>]` with the fare to 2 decimals. `String.format` works like `printf` but returns the String.

**Class `Dispatcher`**

- Constructor takes a `Driver[]`. Private `PricingStrategy pricing`, starting as `StandardPricing`, with `setPricing(...)`. Tracks total fares.
- Distance is Manhattan distance: `|x1 - x2| + |y1 - y2|`, using `Math.abs`.
- `Ride request(String rider, int fromX, int fromY, int toX, int toY, int passengers)`:
  1. Among available drivers whose vehicle capacity is at least `passengers`, pick the one closest to the rider. Break ties by higher rating, then by name alphabetically.
  2. If there is none, print `No driver for <rider>` and return `null`.
  3. Otherwise the trip km is the distance from the rider's position to the destination. Price it with the current strategy, mark the driver unavailable, move them to the destination, add to total fares, and return the ride.
- `void complete(Ride r)`: makes that ride's driver available again.

**Main** (in this order)

1. Drivers: `Ana` in `Sedan("S1")` at (0, 0), rating 4.9. `Ben` in `XL("X1")` at (2, 2), rating 4.7. `Cam` in `Sedan("S2")` at (1, 1), rating 4.8. `Dev` in `Sedan("S3")` at (5, 5), rating 5.0.
2. Requests (print each ride that isn't null):
   - Asha from (1, 0) to (4, 4), 2 passengers
   - Ravi from (2, 1) to (2, 9), 5 passengers
   - Leo from (0, 0) to (0, 1), 6 passengers
3. Switch to `new SurgePricing(1.5)`. Mia from (5, 4) to (5, 5), 1 passenger.
4. Complete Asha's ride. Noah from (4, 3) to (0, 3), 3 passengers.
5. Print `Total fares: $<total>`.

```
Asha -> Ana (Sedan S1), 7 km, $12.50 [Standard]
Ravi -> Ben (XL X1), 8 km, $20.00 [Standard]
No driver for Leo
Mia -> Dev (Sedan S3), 1 km, $7.50 [Surge x1.5]
Noah -> Ana (Sedan S1), 4 km, $12.00 [Surge x1.5]
Total fares: $52.00
```

**Written questions:**

1. Asha is 1 unit from both Ana and Cam. Which rule picked Ana?
2. You're asked to add a 20% student discount. Which classes change, and which don't?
3. `SurgePricing` holds a `StandardPricing` instead of extending it. What's one advantage of that?

### A8. Vending machine (hard)

The state pattern: the machine's behavior changes completely depending on what state it's in, without a single if/else on a state name.

**Class `Item`**

- Private `code`, `name`, `priceCents` (int) and `quantity` (int), with getters and `decrement()`.

**Interface `VendingState`**

- `void insertCoin(VendingMachine m, int cents);`, `void selectItem(VendingMachine m, String code);`, `void cancel(VendingMachine m);`

**Class `VendingMachine`**

- Private `Item[] items`, `balance` and `sales` (cents), and `VendingState state` starting as an `IdleState`.
- `insertCoin`, `selectItem` and `cancel` delegate to the current state. Before delegating, `insertCoin` rejects anything other than 5, 10, 25 or 100 with `Rejected coin: <cents>`.
- Helpers the states use: `findItem(String code)` (returns `null` if not found), `addBalance`, `getBalance`, `clearBalance`, `recordSale`, `isSoldOut()` (every item at quantity 0), `setState`, `getSales`.
- `static String money(int cents)`: returns the cents as dollars, for example `$1.50`.

**States**

- `IdleState`: inserting a coin adds it, prints `Balance: <money>` and switches to `HasMoneyState`. Selecting prints `Insert money first`. Cancel prints `Nothing to cancel`.
- `HasMoneyState`:
  - Inserting a coin adds it and prints the balance.
  - Selecting: unknown code prints `Invalid code: <code>`. Quantity 0 prints `Sold out: <name>`. Not enough balance prints `Insufficient funds: <name> costs <money>`. In all three cases the state doesn't change.
  - Otherwise dispense: decrement, subtract the price, record the sale and print `Dispensed <name>`. If any balance is left, print `Change returned: <money>` and clear it. Then if the machine is sold out, print `Machine is now sold out` and switch to `SoldOutState`. Otherwise switch to `IdleState`.
  - Cancel prints `Refunded <money>`, clears the balance and switches to `IdleState`.
- `SoldOutState`: inserting a coin prints `Machine sold out, returned <money>` and keeps nothing. Selecting prints `Machine sold out`. Cancel prints `Nothing to cancel`.

**Main** (in this order)

Items: `Item("A1", "Chips", 125, 1)` and `Item("B2", "Soda", 150, 2)`.

1. Select A1. Insert 100. Insert 3. Select B2. Insert 25. Select Z9. Select A1.
2. Insert 100 twice. Select A1. Select B2.
3. Insert 25. Cancel.
4. Insert 100, 25, 25. Select B2.
5. Insert 25. Print `Total sales: <money>`.

```
Insert money first
Balance: $1.00
Rejected coin: 3
Insufficient funds: Soda costs $1.50
Balance: $1.25
Invalid code: Z9
Dispensed Chips
Balance: $1.00
Balance: $2.00
Sold out: Chips
Dispensed Soda
Change returned: $0.50
Balance: $0.25
Refunded $0.25
Balance: $1.00
Balance: $1.25
Balance: $1.50
Dispensed Soda
Machine is now sold out
Machine sold out, returned $0.25
Total sales: $4.25
```

**Written questions:**

1. Sketch what `selectItem` would look like with a `String state` field and if/else instead. Which version is easier to read?
2. A `MaintenanceState` is needed, where every action prints `Under maintenance`. What do you add, and what do you change?

### A9. Event bus (hard)

The observer pattern, which is how notification systems, UI buttons and logging pipelines are wired. Publishers never know who's listening.

**Interface `EventListener`**

- `void onEvent(String type, String data);`

**Listeners**

- `Logger`: prints `[LOG] <type>: <data>` and counts every event it logs. `getTotal()`.
- `EmailAlert`: constructor takes an address. Prints `Email to <address>: <data>`.
- `WelcomeMessenger`: prints `Welcome, <data>!`.
- `Counter`: constructor takes a `String[]` of event types to track and keeps a matching `int[]` of counts. Prints nothing. `getCount(String type)` returns the count, or 0 for an untracked type.

**Class `Subscription`**

- Holds an event type and an `EventListener`, with getters.

**Class `EventBus`**

- Private `Subscription[]` of capacity 10 and a count.
- `subscribe(String type, EventListener listener)`.
- `unsubscribe(String type, EventListener listener)`: removes the first subscription with that type and that exact listener object (`==`), shifting the rest left.
- `publish(String type, String data)`: calls every listener subscribed to that type, in subscription order. If none, print `No listeners for <type>`.

**Main** (in this order)

1. Create one `Logger`, `EmailAlert("oncall@company.com")`, `WelcomeMessenger` and `Counter(new String[]{"signup", "error", "payment"})`.
2. Subscribe: signup→logger, signup→counter, error→logger, error→alert, error→counter, signup→welcome.
3. Publish: signup `"asha"`, error `"disk full"`, payment `"$20"`.
4. Unsubscribe the alert from error. Publish: error `"timeout"`, signup `"ravi"`.
5. Print ` signup events:  `, ` error events:  ` and ` payment events:  ` with the counter's numbers, then `Logged: <total>`.

```
[LOG] signup: asha
Welcome, asha!
[LOG] error: disk full
Email to oncall@company.com: disk full
No listeners for payment
[LOG] error: timeout
[LOG] signup: ravi
Welcome, ravi!
signup events: 2
error events: 2
payment events: 0
Logged: 4
```

**Written questions:**

1. `publish` calls four different kinds of listener without knowing any of their class names. What makes that possible?
2. Why does `unsubscribe` compare listeners with `==` rather than `.equals`?
3. Name two real systems you use daily that probably work like this.

### A10. Restaurant ordering system (capstone, open-ended)

No class list this time. You design it, the way an intern design interview works. The brief gives behaviors, not structure.

**The brief:** a counter-service restaurant needs software to take orders, send them to the kitchen and track the day's sales.

**Required behaviors**

1. **Menu items** of at least three kinds: single food items, drinks with a size (S, M, L) that changes the price, and combos. A combo contains other menu items, and its price is their sum minus 15%.
2. **Customization:** some items accept add-ons (extra cheese +$0.75, no onions free). Not every item is customizable.
3. **Orders:** add and remove items, show the subtotal, 7.75% tax and the total. Each order gets an order number from a counter.
4. **Order status:** placed, preparing, ready, picked up, in that order only. An invalid jump (placed straight to picked up) is rejected with a message.
5. **Discounts:** no discount, a percentage coupon, or buy-one-get-one-free on drinks. One discount per order, swappable before payment.
6. **Two views of an order:** a customer receipt with prices, and a kitchen ticket with items and add-ons but no prices.
7. **Reorder:** create a new order that copies an old one. Changing the new order must not affect the old one.
8. **End of day:** number of orders, total revenue and the best-selling menu item.

**Constraints**

- At least one abstract class and at least two interfaces, one with a `default` method.
- Polymorphic arrays (a single array holding different item types).
- Override `toString()` and `equals()` somewhere it actually matters.
- Arrays only. No `ArrayList`, generics or exceptions.

**Deliverables**

1. A design sketch in comments at the top: each class and interface, what it's responsible for, and why each is an abstract class, interface or concrete class.
2. The code.
3. A `main` that runs a full day: at least four orders, including a combo, a customized item, a coupon, a BOGO drink order, a rejected status change and a reorder. Then the end-of-day summary.
4. Answers to these design questions:
   - Where do the patterns from A5, A7 and A8 show up in your design, if anywhere?
   - What changes if the restaurant adds a fourth item type, like desserts?
   - Which part of your design are you least happy with, and why?

There's no expected output to match here. When you send it, I'll review the design itself, not just whether it runs.
