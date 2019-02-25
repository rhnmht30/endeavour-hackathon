# Generated by Django 2.1.7 on 2019-02-23 16:52

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('login_signup', '0006_cardetails'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='myuser',
            name='uniq_id',
        ),
        migrations.AddField(
            model_name='myuser',
            name='id',
            field=models.AutoField(auto_created=True, default=1, primary_key=True, serialize=False, verbose_name='ID'),
            preserve_default=False,
        ),
        migrations.AlterField(
            model_name='cardetails',
            name='carseats',
            field=models.IntegerField(),
        ),
    ]