from kivy.uix.screenmanager import Screen
from kivy.uix.textinput import TextInput
from kivy.app import App
from kivy.lang import Builder
from kivy.core.window import Window
from kivy.uix.boxlayout import BoxLayout
from kivy.properties import BooleanProperty, ListProperty, StringProperty, ObjectProperty, NumericProperty

Window.size = (450, 525)


class display(Screen):

    def add_more(self):
        self.ids.rows.add_row()

    def insert_value(self):
        values = []
        rows = self.ids.rows
        for row in reversed(rows.children):
            for ch in row.children:
                if isinstance(ch, TextInput):
                    values.append(ch.text)
        print(values)


class Row(BoxLayout):
    button_text = StringProperty("")
    id = ObjectProperty(None)


class Rows(BoxLayout):
    orientation = "vertical"
    row_count = 0

    def __init__(self, **kwargs):
        super(Rows, self).__init__(**kwargs)
        self.add_row()

    def add_row(self):
        self.row_count += 1
        self.add_widget(Row(button_text=str(self.row_count),id=str("test"+str(self.row_count))))


class test(App):

    def build(self):
        self.root = Builder.load_file('test.kv')
        return self.root

if __name__ == '__main__':
    test().run()