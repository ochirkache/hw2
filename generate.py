#!/usr/bin/python3
# -*- coding: utf-8 -*-
import random
import typing

AMOUNT_OF_RECORDS = 15
RESULT_FILE = "sample.csv"
persons = {46123: 18, 75821: 25, 84620: 30, 81213: 61, 98213: 49, 12334: 37, 52739: 21, 12383: 70}

class Passport:
    number: int
    age: int
    month: int
    salary: int # RUB
    trips: int
    
    def __init__(self, number: int, age: int):
        self.number = number
        self.age = age
        return
    
    def representation(self):
        return f"{self.number},{self.month},{self.salary},{self.age},{self.trips}\n"

def random_person():
    return random.choice(list(persons.keys()))

def month():
    return random.randint(1, 12)

def salary():
    return random.randint(20_000, 200_000)

def age():
    return random.randint(18, 70)

def trips():
    return random.randint(0, 10)


if __name__ == "__main__":
    print("Generating...")
    passports = []
    data = []
    for i in range(0, AMOUNT_OF_RECORDS):
        person = random_person()
        age = persons[person]
        passport = Passport(person, age)
        passport.month = month()
        passport.salary = salary()
        passport.trips = trips()

        month_filled = False
        for item in passports:
            if item.number == passport.number and item.month == passport.month:
                month_filled
                break
        
        if month_filled:
            continue

        passports.append(passport)
        data.append(passport.representation())
    
    with open(RESULT_FILE, "w") as file:
        for item in data:
            file.write(item)
        
    print("Done")

