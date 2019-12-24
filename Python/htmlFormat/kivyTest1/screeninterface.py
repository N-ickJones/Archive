import kivy
from kivy.app import App
from kivy.uix.scatter import Scatter
from kivy.uix.floatlayout import FloatLayout
from kivy.uix.label import Label
from kivy.uix.button import Button
from kivy.uix.textinput import TextInput
from kivy.uix.boxlayout import BoxLayout


class ScatterTextWidget(BoxLayout):
    pass


class ScreenInterface(App):
    def build(self):
        return ScatterTextWidget()


if __name__ == "__main__":
    ScreenInterface().run()
