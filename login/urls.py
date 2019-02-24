from django.conf.urls import url
from . import views

urlpatterns = [
    url(r'^check/$',views.index,name='check'),
    url(r'^login/$',views.login, name='login'),
    url(r'^addcar/$',views.addCar,name='addcar'),
    url(r'^register/$',views.register,name='register'),
    url(r'^addpool/$',views.addPool, name='addpool'),
    url(r'^profile/$',views.profile, name='profile'),
    url(r'^getpooldetails/$', views.getPoolDetails, name='getpooldetails'),
    url(r'^addUsertoPool/$', views.addUsertoPool, name='addUsertoPool'),
    url(r'^getcurrentPools/$', views.getcurrentPools, name='getcurrentPools'),
    url(r'^endpool/$', views.endpool, name='endpool'),
]