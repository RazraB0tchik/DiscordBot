from flask import Flask
from tools import music_parser_vk

app = Flask(__name__)


@app.route('/')
def hello_world():  # put application's code here
    music_parser_vk.load_music()


if __name__ == '__main__':
    app.run()
