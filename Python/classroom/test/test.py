import time
import random
from threading import Thread


class SleepyThread(Thread):

    def __init__(self, number, sleepMax):
        Thread.__init__(self, name="Thread " + str(number))
        self.sleepInterval = random.randint(1, sleepMax)

    def run(self):
        print("%s starting, with sleep interval: %d seconds\n" % \
              (self.getName(), self.sleepInterval))
        time.sleep(self.sleepInterval)
        print("%s waking up\n" % self.getName())


def main():
    num_threads = int(input("Enter the number of threads: "))
    sleep_max = int(input("Enter the maximum sleep time: "))
    thread_list = []
    for count in range(num_threads):
        thread_list.append(SleepyThread(count + 1, sleep_max))
    for thread in thread_list:
        thread.start()


if __name__ == "__main__":
    main()
