from flask import Blueprint, render_template, request, flash, redirect, url_for, send_from_directory
from .models import User
from werkzeug.security import generate_password_hash, check_password_hash
from . import db
from flask_login import login_user, login_required, logout_user, current_user


auth = Blueprint('auth', __name__)


# @auth.route("/static/<path:filename>")
# def staticfiles(filename):
#     return send_from_directory(app.config["STATIC_FOLDER"], filename)


@auth.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        email = request.form.get('email')
        password = request.form.get('password')

        user = User.query.filter_by(email=email).first()
        if user:
            if check_password_hash(user.password, password):
                flash('Prihlaseny !', category='success')
                login_user(user, remember=True)
                return redirect(url_for('views.home'))
            else:
                flash('Nespravne heslo, skus znova.', category='error')
        else:
            flash('Email neexistuje.', category='error')
    return render_template('login.html', user=current_user)


@auth.route('/logout')
@login_required
def logout():
    logout_user()
    return redirect(url_for('auth.login'))


@auth.route('/sign-up', methods=['GET', 'POST'])
def sign_up():
    if request.method == 'POST':
        email = request.form.get('email')
        first_name = request.form.get('firstName')
        password1 = request.form.get('password1')
        password2 = request.form.get('password2')

        user = User.query.filter_by(email=email).first()
        if user:
            flash('Email uz existuje.', category='error')
        elif len(email) < 4:
            flash('Email musi byt vacsi ako 3 znaky.', category='error')
        elif len(first_name) < 2:
            flash('Meno musi mat viac ako 1 znak.', category='error')
        elif password1 != password2:
            flash('Heslo nie je rovnake.', category='error')
        elif len(password1) < 8:
            flash('Heslo musi mat najmenej 8 znakov.', category='error')
        else:
            new_user = User(email=email, first_name=first_name, password=generate_password_hash(password1, method='sha256'))
            db.session.add(new_user)
            db.session.commit()
            login_user(new_user, remember=True)
            flash('Ucet vytvoreny!', category='success')
            return redirect(url_for('views.home'))

    return render_template('sign_up.html', user=current_user)
