import kivy
import html_Format
import re
from kivy.app import App
from kivy.uix.button import Button
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.label import Label
from kivy.uix.textinput import TextInput
from kivy.core.window import Window
from kivy.properties import ObjectProperty
Window.clearcolor = (0.5, 0.5, 0.5, 1)
Window.size = (640, 360)
kivy.require('1.10.1')


class MyApp(App):

    # Call the base class constructor
    # Note that this is where it will pick up on any stuff you did in the .kv file (if you have one)
    def __init__(self, **kwargs):
        self.check = True
        self.check2 = True
        self.lbl1 = Label(text="")
        self.txt1 = TextInput(text='', multiline=False)
        self.line = ""
        self.m = "<iframe></iframe>"
        self.tag_empty = r'(<[^\/!][^>|]*?>)((?:|.+?))(<[^>]*?>)'
        self.tag = r'(<[^\/!][^>]*?>)'
        self.style = r'style=(?:|\s+?|)\"([^\"]+?|)\"'
        self.align = r'align=(?:|\s+?|)\"([^\"]+?|)\"'
        self.width = r'width=(?:|\s+?|)\"([0-9|\s|px]+?)\"'
        self.height = r'height=(?:|\s+?|)\"([0-9|\s|px]+?)\"'
        self.clear = r'clear=(?:|\s+?|)\"([a-z]+|)\"'
        self.frameborder = r'frameborder=(?:|\s+?|)\"([^\"]+?|)\"'
        self.border = r'border=(?:|\s+?|)\"([^\"]+?|)\"'
        self.cellpadding = r'cellpadding=(?:|\s+?|)\"([^\"]+?|)\"'
        self.extra_spaces = r'\s\s+'
        self.extra_semi_colons = r';;|;;+'
        self.extra_colons = r'::|::+'
        self.extra_style = r'style=(?:|\s+?|)\"(?:|\s+?)\"'
        self.extra_space_end = r'\s+>'
        self.extra_px = r'\s0px'
        self.font_family = r'font-family:(?:|\s+|)([^\"]+?|);'  # Need to figure out solution for no semi-colon case
        self.face = r'face=(?:|\s+?)\"([^\"]+?|)\"'
        self.margin_width = r'marginwidth=(?:\s+?|)\"([^\"]+?|)\"'
        self.margin_height = r'marginheight=(?:\s+?|)\"([^\"]+?|)\"'
        self.sizcache = r'sizcache=(?:\s+?|)\"([^\"]+?|)\"'
        self.sizset = r'sizset=(?:\s+?|)\"([^\"]+?|)\"'
        self.value = ''
        self.no_title_needed = \
            r'title=(?:|\s+?|)\"([^\"]+?|)\"|type=(?:|\s+?|)\"([\s]*?hidden[' \
            r'\s]*?)\"|type=(?:|\s+?|)\"([\s]*?submit[\s]*?)\"'
        self.ntn_iframe = r'title=(?:|\s+?|)\"([^\"]+?|)\"'
        self.source = 'workbench.html'
        Window.bind(on_key_down=self._on_keyboard_down)
        super(MyApp, self).__init__(**kwargs)

    def build(self):
        layout = BoxLayout(padding=10, orientation='vertical')
        btn1 = Button(size=1, text="Next Line")
        btn1.bind(on_press=self.button_clicked)
        btn2 = Button(width=25, height=25, text="Clear After Each Step - On")
        btn2.bind(on_press=self.button_clicked2)
        btn3 = Button(width=25, height=25, text="Fix depreciated HTML Style")
        btn3.bind(on_press=self.button_clicked3)
        layout.add_widget(self.txt1)
        layout.add_widget(self.lbl1)
        layout.add_widget(btn1)
        layout.add_widget(btn2)
        layout.add_widget(btn3)

        return layout

    def _on_keyboard_down(self, instance, keyboard, keycode, text, modifiers):
        print keycode
        if self.txt1.focus and (keycode == 40):
            if 'skip' not in self.txt1.text:
                self.lbl1.text = re.sub(r'<iframe', ' Line changed to <iframe ' + 'title="' + self.txt1.text + '"',
                                        self.m,
                                        flags=re.IGNORECASE)
            else:
                self.lbl1.text = self.m
                self.check = True
            self.check = True

    # button click function
    def button_clicked(self, btn):
        # define M before Here
        no_title_iframe = r'title=(?:|\s+?|)\"([^\"]+?|)\"'
        if self.check is True:
            if '<iframe' in self.m:
                if re.search(no_title_iframe, self.m, flags=re.IGNORECASE) is None:
                    self.lbl1.text = "Enter a title for <iframe> tag type skip to go to next."
                    if self.check2 is True:
                        self.txt1.text = ""
                    btn.text = "Input Title"

            self.check = False
        else:
            if 'skip' not in self.txt1.text:
                self.lbl1.text = re.sub(r'<iframe', ' Line changed to <iframe ' + 'title="' + self.txt1.text + '"',
                                        self.m, flags=re.IGNORECASE)
                btn.text = "Next Line"
            else:
                self.lbl1.text = self.m
            self.check = True

    def button_clicked2(self, btn2):
        if self.check2 is True:
            btn2.text = "Clear After Each Step - Off"
            self.check2 = False
        else:
            btn2.text = "Clear After Each Step - On"
            self.check2 = True

    def button_clicked3(self, btn3):
        html_Format.repair_file(self.source)


# run app
if __name__ == "__main__":
    MyApp().run()
