# Generated by Django 2.1.7 on 2019-05-06 11:08

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('login', '0011_userpooling_poolingwithid'),
    ]

    operations = [
        migrations.AddField(
            model_name='startpooling',
            name='pooledbyid',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.SET_NULL, to='login.MyUser'),
        ),
    ]