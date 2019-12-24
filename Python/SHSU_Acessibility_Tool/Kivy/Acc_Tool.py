# Purpose: GUI for Accessibility Tool
# Author: Nicholas Jones (SHSU Student Worker)
# Date: 07.27.2018
# Version: 1.0

# 1 TODO Automate Aria-labelledby or suggestion for id Backwards for loop
# 2 TODO implement Previous Button

import kivy
import html_Format
import html_Fix
import link_Text
from kivy.app import App
from kivy.uix.button import Button
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.label import Label
from kivy.uix.textinput import TextInput
from kivy.core.window import Window
# from kivy.clock import Clock
Window.clearcolor = (0.5, 0.5, 0.5, 1)
Window.size = (1000, 500)
kivy.require('1.10.1')


class AccessibilityToolApp(App):
    def __init__(self, **kwargs):
        # Colors
        self.color_default = (1, 1, 1, 1)
        self.color_inactive = (1, .8, .8, 1)
        self.color_active = (.8, 1, .8, 1)
        self.color_selected = (1, 1, 1, 0.8)
        # Strings
        self.file_name = "workbench.html"
        self.linkFixSelected = ''
        self.button_clearHold = ''
        self.button_unset_04Hold = ''
        self.button_unset_05Hold = ''
        self.auto_mate = ''
        # Boolean
        self.clear_toggle = True
        self.enter_recent = False
        self.linkText = False
        self.checkAttribute = False
        self.checkAttribute2 = False
        self.link_Click = False
        self.link_Active = False
        self.link_Start = False
        self.link_Next = False
        self.link_Fix_Performed = False
        self.html_Active = False
        self.html_Click = False
        self.htmlIndex = False
        self.html_Start = False
        self.html_Next = False
        self.html_Fix_Performed = False
        # Integers
        self.index = 0
        self.linkIndex = 0
        self.fixes_made = 0
        # Arrays
        self.matches = []
        self.lines = []
        self.matchedLine = []
        self.matchedLineNumber = []
        self.add_aria = []
        # Labels
        self.label_top = Label(text="Select A Tool")
        self.label_mid = Label(text="")
        self.label_tools = Label(text="Tools")
        self.label_options = Label(text="Options")
        # TextInput
        self.input_top = TextInput(text='', multiline=False)
        # Buttons
        self.button_prev = Button(text="Previous",
                                  background_color=self.color_inactive)
        self.button_prev.bind(on_release=self.button_click_previous)
        self.button_unset_01 = Button(text="",
                                      background_color=self.color_inactive)
        self.button_unset_01.bind(on_release=self.button_click_fill01)
        self.button_next = Button(text="Next",
                                  background_color=self.color_inactive)
        self.button_next.bind(on_release=self.button_click_next)
        self.button_start = Button(text="Start",
                                   background_color=self.color_inactive)
        self.button_start.bind(on_release=self.button_click_start)
        self.button_unset_02 = Button(text='',
                                      background_color=self.color_inactive)
        self.button_unset_02.bind(on_release=self.button_click_fill02)
        self.button_reset = Button(text="Reset",
                                   background_color=self.color_inactive)
        self.button_reset.bind(on_release=self.button_click_reset)
        self.button_html_format = Button(text="HTML is used to format content",
                                         background_color=self.color_active)
        self.button_html_format.bind(on_release=self.button_click_html_format)
        self.button_link = Button(text="Link text used for multiple different destinations",
                                  font_size='14',
                                  background_color=self.color_active)
        self.button_link.bind(on_release=self.button_click_link_text)
        self.button_unset_03 = Button(text="Set Aria-Lablledby ids",
                                      background_color=self.color_inactive)
        self.button_unset_03.bind(on_release=self.button_click_fill03)
        self.button_clear = Button(text="Clear After Each Step - On",
                                   background_color=self.color_default)
        self.button_clear.bind(on_release=self.button_click_toggle_clear)
        self.button_unset_04 = Button(text="",
                                      background_color=self.color_default)
        self.button_unset_04.bind(on_release=self.button_click_fill04)
        self.button_unset_05 = Button(text="",
                                      background_color=self.color_default)
        self.button_unset_05.bind(on_release=self.button_click_fill05)
        # Window Events
        Window.bind(on_key_down=self._on_keyboard_down, on_key_up=self._on_keyboard_up)
        # Initialize Variables
        super(AccessibilityToolApp, self).__init__(**kwargs)

    def build(self):
        # HBox1
        hbox1 = BoxLayout(orientation="horizontal", padding=5)
        hbox1.add_widget(self.button_prev)
        hbox1.add_widget(self.button_unset_01)
        hbox1.add_widget(self.button_next)

        # HBox2
        hbox2 = BoxLayout(orientation="horizontal", padding=5)
        hbox2.add_widget(self.button_start)
        hbox2.add_widget(self.button_unset_02)
        hbox2.add_widget(self.button_reset)

        # HBox3
        hbox3 = BoxLayout(orientation="horizontal", padding=5)
        hbox3.add_widget(self.button_html_format)
        hbox3.add_widget(self.button_link)
        hbox3.add_widget(self.button_unset_03)

        # HBox4
        hbox4 = BoxLayout(orientation="horizontal", padding=5)
        hbox4.add_widget(self.button_clear)
        hbox4.add_widget(self.button_unset_04)
        hbox4.add_widget(self.button_unset_05)

        # VBox
        vbox = BoxLayout(orientation="vertical", padding=5)
        vbox.add_widget(self.label_top)
        vbox.add_widget(self.input_top)
        vbox.add_widget(self.label_mid)
        vbox.add_widget(hbox1)
        vbox.add_widget(hbox2)
        vbox.add_widget(self.label_tools)
        vbox.add_widget(hbox3)
        vbox.add_widget(self.label_options)
        vbox.add_widget(hbox4)
        return vbox

    def _on_keyboard_down(self, instance, keyboard, keycode, text, modifiers):
        if self.link_Click:
            if self.checkAttribute is False and self.checkAttribute2 is True:
                if self.input_top.focus and keycode == 40:
                    self.matchedLine[self.linkIndex] = link_Text.fix(str(self.matchedLine[self.linkIndex]),
                                                                     str(self.linkFixSelected),
                                                                     str(self.input_top.text))
                    self.label_mid.text = self.matchedLine[self.linkIndex]
                    self.linkIndex += 1
                    self.input_top.background_color = self.color_default

                    if self.linkIndex == len(self.matchedLine):
                        # Finished
                        self.checkAttribute = False
                        self.label_top.text = "Finished"
                        link_Text.update_file(self.file_name, self.matchedLine, self.matchedLineNumber)
                        self.link_Click = False
                        self.button_link.background_color = self.color_default
                        self.link_Fix_Performed = True
                        if self.html_Fix_Performed:
                            self.button_html_format.background_color = self.color_default
                        else:
                            self.button_html_format.background_color = self.color_active

                    else:
                        # Continue
                        self.checkAttribute = True
                        self.enter_recent = True
                        self.label_top.text = "Click Next to Continue"
                        self.button_next.background_color = self.color_active
                else:
                    pass
            else:
                pass
        elif self.html_Click:
            if (self.html_Start or self.html_Next) and self.input_top.focus and keycode == 40:
                self.html_Start = False
                self.matchedLine[self.htmlIndex] = html_Fix.fix(str(self.matchedLine[self.htmlIndex]),
                                                                str(self.input_top.text))
                self.label_mid.text = self.matchedLine[self.htmlIndex]
                self.htmlIndex += 1
                self.input_top.background_color = self.color_default
                if self.htmlIndex == len(self.matchedLine):
                    # Finished
                    self.checkAttribute = False
                    self.label_top.text = "Finished"
                    html_Fix.update_file(self.file_name, self.matchedLine, self.matchedLineNumber)
                    self.html_Click = False
                    self.html_Next = False
                    self.button_html_format.background_color = self.color_default
                    if self.link_Fix_Performed:
                        self.button_link.background_color = self.color_default
                    else:
                        self.button_link.background_color = self.color_active
                    self.html_Fix_Performed = True
                else:
                    # Continue
                    self.checkAttribute = True
                    self.checkAttribute2 = True
                    self.enter_recent = True
                    self.label_top.text = "Click Next to Continue"
                    self.button_next.background_color = self.color_active
            else:
                pass
        else:
            pass

    def _on_keyboard_up(self, instance, keyboard, keycode):
        if self.link_Click and keycode == 40:
            # Start Enter By Passes
            if self.link_Active is True:
                self.button_click_start(self.button_start)
            elif self.link_Start is True or self.link_Next is True:
                if 'aria' in self.linkFixSelected:
                    self.button_click_toggle_clear(self.button_clear)
                elif 'title' in self.linkFixSelected:
                    self.button_click_fill04(self.button_unset_04)
                elif 'name' in self.linkFixSelected:
                    self.button_click_fill05(self.button_unset_05)
                self.link_Next = False
            elif self.checkAttribute is True:
                if self.enter_recent is True:
                    self.enter_recent = False
                else:
                    self.button_click_next(self.button_next)
            else:
                pass
        elif self.html_Click and keycode == 40:
            if self.html_Active is True:
                self.button_click_start(self.button_start)
            elif self.html_Start is True and self.html_Next is True:
                pass
            elif self.checkAttribute is True:
                if self.enter_recent is True:
                    self.enter_recent = False
                else:
                    self.button_click_next(self.button_next)
            else:
                pass
        else:
            pass

    def button_click_previous(self, btn01):
        pass

    def button_click_fill01(self, btn02):
        pass

    def button_click_next(self, btn03):
        if self.link_Click:
            if self.checkAttribute:
                if self.checkAttribute2 is False:
                    self.button_clear.text = self.button_clearHold
                    self.button_unset_04.text = self.button_unset_04Hold
                    self.button_unset_05.text = self.button_unset_05Hold
                    self.button_clear.background_color = self.color_default
                    self.button_unset_04.background_color = self.color_default
                    self.button_unset_05.background_color = self.color_default
                    self.button_next.background_color = self.color_inactive
                    self.label_top.text = "Input the Value for " + self.linkFixSelected + " Attribute then Press Enter"
                    self.checkAttribute = False
                    self.checkAttribute2 = True
                    self.input_top.background_color = self.color_active
                    self.input_top.focus = True
                    self.auto_mate = 'or Press Enter To Use ' + self.linkFixSelected

                elif self.checkAttribute2:
                    # Reset check Attribute
                    self.checkAttribute = False
                    self.checkAttribute2 = False
                    # Save to Reset Button Values
                    self.button_clearHold = self.button_clear.text
                    self.button_unset_04Hold = self.button_unset_04.text
                    self.button_unset_05Hold = self.button_unset_05.text
                    # Options For Next Phase
                    self.label_top.text = 'Select: Title, Aria-labelledby, or Name ' + self.auto_mate
                    self.button_clear.text = "aria-labelledby"
                    self.button_unset_04.text = 'title'
                    self.button_unset_05.text = 'name'
                    self.button_clear.background_color = self.color_active
                    self.button_unset_04.background_color = self.color_active
                    self.button_unset_05.background_color = self.color_active
                    self.button_next.background_color = self.color_inactive
                    if self.clear_toggle is True:
                        self.input_top.text = ''
                    else:
                        pass
                    self.link_Next = True
                    # Next Option
                    self.label_mid.text = "Line " + str(self.matchedLineNumber[self.linkIndex]) + "  " + \
                                          self.matchedLine[self.linkIndex]
                else:
                    pass
            else:
                pass

        elif self.html_Click:
            if self.checkAttribute:
                self.button_next.background_color = self.color_inactive
                self.label_top.text = "Input the Value for the title then Press Enter"
                self.checkAttribute = False
                if self.clear_toggle is True:
                    self.input_top.text = ''
                else:
                    pass
                self.label_mid.text = "Line " + str(self.matchedLineNumber[self.htmlIndex]) + "  " + \
                                      self.matchedLine[self.htmlIndex]
                self.html_Next = True
                self.input_top.background_color = self.color_active
                self.input_top.focus = True
            else:
                pass
        else:
            pass

    def button_click_start(self, btn1):
        if self.link_Active:  # and self.matches and self.lines:
            self.link_Active = False
            i = 0
            for line in self.lines:
                for match in self.matches:
                    if match in line:
                        self.matchedLine.append(line)
                        self.matchedLineNumber.append(i)
                    else:
                        pass
                i += 1
            # Save to Reset Button Values
            self.button_clearHold = self.button_clear.text
            self.button_unset_04Hold = self.button_unset_04.text
            self.button_unset_05Hold = self.button_unset_05.text
            # Options For Next Phase
            self.label_top.text = 'Select title, aria-labelledby, or name Below'
            self.button_clear.text = "aria-labelledby"
            self.button_unset_04.text = 'title'
            self.button_unset_05.text = 'name'
            # First Option
            self.linkIndex = 0
            self.label_mid.text = "Line " + str(self.matchedLineNumber[self.linkIndex]) + "  " + \
                                  self.matchedLine[self.linkIndex]
            self.button_start.background_color = self.color_inactive
            self.button_clear.background_color = self.color_active
            self.button_unset_04.background_color = self.color_active
            self.button_unset_05.background_color = self.color_active
            self.link_Start = True

        elif self.html_Active:
            self.html_Active = False
            i = 0
            for line in self.lines:
                for match in self.matches:
                    if match in line:
                        self.matchedLine.append(line)
                        self.matchedLineNumber.append(i)
                    else:
                        pass
                i += 1
            self.htmlIndex = 0
            self.label_top.text = 'Input a Title then Press Enter'
            self.label_mid.text = "Line " + str(self.matchedLineNumber[self.linkIndex]) + "  " + \
                                  self.matchedLine[self.htmlIndex]
            self.button_start.background_color = self.color_inactive
            self.input_top.background_color = self.color_active
            self.html_Start = True
            self.input_top.focus = True

        else:
            pass

    # Stop Button
    def button_click_fill02(self, btn2):
        pass

    def button_click_reset(self, btn3):
        pass

    def button_click_html_format(self, btn4):
        if self.html_Click is False and self.link_Click is False:  # Boolean
            self.html_Click = True
            self.fixes_made = html_Format.repair_file(self.file_name)
            del self.lines[:]
            del self.matches[:]
            self.lines = html_Fix.get_lines(self.file_name)
            self.matches = html_Fix.search_file(self.file_name)
            if len(self.matches) < 1:
                self.label_top.text = "Select Another Tool"
                self.label_mid.text = str(self.fixes_made) + " Bugs Fixed and HTML Reformatted"  # Number of Fixes
                self.html_Active = False
                self.html_Click = False
                self.button_html_format.background_color = self.color_default
            elif len(self.matches) == 1:
                self.label_top.text = str(len(self.matches)) + \
                                 " Match For Input without a Label or Title"
                self.label_mid.text = str(self.fixes_made) + " Bugs Fixed - " + \
                    "Press The Start-Button Or Enter To Fix The Remaining Bugs"
                self.html_Active = True
                self.button_html_format.background_color = self.color_inactive
                self.button_link.background_color = self.color_inactive
                self.button_start.background_color = self.color_active
            else:
                self.label_top.text = str(len(self.matches)) + \
                                 " Matches For Input without a Label or Title"
                self.label_mid.text = str(self.fixes_made) + " Bugs Fixed - " + \
                    "Press The Start-Button Or Enter To Fix The Remaining Bugs"
                self.html_Active = True
                self.button_html_format.background_color = self.color_inactive
                self.button_link.background_color = self.color_inactive
                self.button_start.background_color = self.color_active
        else:
            self.button_html_format.background_color = self.color_inactive

    def button_click_link_text(self, btn5):
        if self.html_Active is False and self.link_Click is False:
            self.link_Click = True
            del self.lines[:]
            self.lines = link_Text.get_lines(self.file_name)
            del self.matches[:]
            self.matches = link_Text.search_file(self.file_name)
            if len(self.matches) < 1:
                self.label_top.text = "No Matches For Link Text Used For Multiple Different Destinations"
                self.label_mid.text = "No Errors To Be Fixed"
                self.link_Active = False
                self.link_Click = False
                self.button_link.background_color = self.color_default
            elif len(self.matches) == 1:
                self.label_top.text = str(len(self.matches)) + \
                                 " Match For Link Text Used For Multiple Different Destinations"
                self.label_mid.text = "Press The Start-Button Or Enter To Fix These Errors"
                self.link_Active = True
                self.button_html_format.background_color = self.color_inactive
                self.button_link.background_color = self.color_inactive
                self.button_start.background_color = self.color_active
            else:
                self.label_top.text = str(len(self.matches)) + \
                                 " Matches For Link Text Used For Multiple Different Destinations"
                self.label_mid.text = "Press The Start-Button Or Enter To Fix These Errors"
                self.link_Active = True
                self.button_html_format.background_color = self.color_inactive
                self.button_link.background_color = self.color_inactive
                self.button_start.background_color = self.color_active
        else:
            self.button_link.background_color = self.color_inactive

    def button_click_fill03(self, btn6):
        if self.html_Active is False and self.link_Click is False:
            if self.link_Fix_Performed is True:
                del self.add_aria[:]
                has_match = False
                for i in range(len(self.lines), -1, -1):
                    if has_match is True:
                        if 'id' in self.lines[i] is None:
                            pass
                        self.add_aria.append(self.lines[i])
                    else:
                        pass
                    for match in self.matchedLine:
                        if match in self.lines[i]:
                            has_match = True
                        else:
                            pass

                self.matchedLine[self.linkIndex] = link_Text.add_aria_id(str(self.matchedLine[self.linkIndex]),
                                                                 str(self.linkFixSelected),
                                                                 str(self.input_top.text))
                self.label_mid.text = self.matchedLine[self.linkIndex]
                self.linkIndex += 1
                self.input_top.background_color = self.color_default
                self.matches = []
                self.lines = []
                self.matchedLine = []
                self.matchedLineNumber = []

            else:
                pass
        else:
            pass

    def button_click_toggle_clear(self, btn7):
        if self.clear_toggle is True and "Clear After Each Step - On" in self.button_clear.text:
            self.button_clear.text = "Clear After Each Step - Off"
            self.clear_toggle = False

        elif self.clear_toggle is False and "Clear After Each Step - Off" in self.button_clear.text:
            self.button_clear.text = "Clear After Each Step - On"
            self.clear_toggle = True
        else:
            pass

        if self.link_Click:
            if (self.link_Start or self.link_Next) and 'aria-labelledby' in self.button_clear.text:
                self.link_Start = False
                self.link_Next = False
                self.linkFixSelected = 'aria'
                self.label_top.text = "You want to add a " + self.linkFixSelected + \
                                      " Attribute? If so, press The Next Button, Enter, or Select Another."
                self.checkAttribute = True
                self.button_next.background_color = self.color_active
                self.button_clear.background_color = self.color_selected
            else:
                pass
        else:
            pass

    def button_click_fill04(self, btn8):
        if self.link_Click:
            if (self.link_Start or self.link_Next) and self.button_unset_04.text is 'title':
                self.link_Start = False
                self.link_Next = False
                self.linkFixSelected = 'title'
                self.label_top.text = "You want to add a " + self.linkFixSelected + \
                                      " Attribute? If so, press The Next Button, Enter, or Select Another."
                self.checkAttribute = True
                self.button_next.background_color = self.color_active
                self.button_unset_04.background_color = self.color_selected
            else:
                pass
        else:
            pass

    def button_click_fill05(self, btn9):
        if self.link_Click:
            if (self.link_Start or self.link_Next) and self.button_unset_05.text is 'name':
                self.link_Start = False
                self.linkFixSelected = 'name'
                self.label_top.text = "You want to add a " + self.linkFixSelected + \
                                      " Attribute? If so, press The Next Button, Enter, or Select Another."
                self.checkAttribute = True
                self.button_next.background_color = self.color_active
                self.button_unset_04.background_color = self.color_selected
            else:
                pass
        else:
            pass


# run app
if __name__ == "__main__":
    AccessibilityToolApp().run()
