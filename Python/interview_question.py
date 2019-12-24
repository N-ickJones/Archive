import dis
import inspect
import sys
import a_mean_game  # NOTE: requires Python version 3.4.x

def game_on():
    ''' I'm not a rat, Agent Kujan. '''
    def easter_egg():
        print('Easter Egg: Mmmm... Cadbury.')
    print("Kaizer Sozegg!")
    sys.exit()
    print('The greatest trick the Easter Bunny ever pulled...')

def main():
    sandbox()
    game_on()
    a_mean_game.game_over()
    a_mean_game.win()


# Howdy!
#
# Thank you for your interest in FrogSlayer and taking the time to apply.
#
# This file is a coding exercise. Please follow the instructions explicitly -
# you may only write code where specified. We've included reference material
# with information to produce a working solution. This is not a trick problem;
# it is solvable within the given constraints.
#
# Please make sure to comment your code and carefully explain what it does
# in your own words. Comments are critical for us to evaluate your
# understanding and your strengths. Also, it's okay if you can't get a fully
# working solution; just submit what you have along with your comments. We're
# more interested in seeing how well you can learn and use something new with
# limited direction.
#
# Instructions:
# Using Python 3.4, and only writing code where indicated, (inside the sandbox
# function), make this program perform the following tasks:
#  1) after sandbox has executed, allow the rest of the main function to run
#  2) prevent game_on from printing "Kaizer Sozegg!" and prevent it from exiting
#  3) unsilence a_mean_game.game_over and prevent it from exiting
#  4) let a_mean_game.win execute normally
#  bonus) find some easter eggs, and execute or display them
#
# Thus, it should minimally output: 
#  "The greatest trick the Easter Bunny ever pulled..."
#  "... was convincing the world he didn't exist."
#  Followed by the FrogSlayer logo
#
# Some useful references:
#
# https://docs.python.org/3.4/library/dis.html
# https://docs.python.org/3.4/library/inspect.html#the-interpreter-stack
# https://docs.python.org/3.4/library/sys.html#sys.settrace
#
# We have had concerns that this kind of hackery would be required on the job
# at FrogSlayer. I want to reassure you that code quality is important to us
# and that this is only for sake of producing a problem that gets outside of 99%
# of developers' experiences.
#

def sandbox():
    #####################################################
    # Begin sandbox
    # you can only code in this sandbox
    #####################################################

    pass

    #####################################################
    # End sandbox
    #####################################################


if __name__ == '__main__':
    main()
