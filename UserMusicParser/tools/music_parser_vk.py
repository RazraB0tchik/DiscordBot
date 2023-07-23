# import requests
import vk_audio
import vk_api
from vk_audio import VkAudio
import re
from flask import session

# owner = 328356503


def load_music():
        re.search()

        login_user = "89244273168"
        password_user = "sxipm68"

        vk_session = vk_api.VkApi(login=login_user,password=password_user,app_id=2685278)
        vk_session.auth()
        print(vk_session.token)
        vk_user_audio = VkAudio(vk_session)

        # data = vk_user_audio.load(owner_id=328356503)
        auds = vk_user_audio.get_only_audios(owner_id=328356503, offset=10)
        for trek in auds:
          print(trek)
        # second_music = data.Audios[2]
        # print(second_music)
