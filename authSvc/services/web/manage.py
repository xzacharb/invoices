from flask.cli import FlaskGroup

# from project_auth import app, db, User
from project_auth import create_app

appli = create_app()

cli = FlaskGroup(appli)

if __name__ == "__main__":
    cli()

# if __name__ == '__main__':
#     app.run(debug=True)

# cli = FlaskGroup(app)

# @cli.command("create_db")
# def create_db():
#     db.drop_all()
#     db.create_all()
#     db.session.commit()

# @cli.command("seed_db")
# def seed_db():
#     db.session.add(User(email="michael@mherman.org"))
#     db.session.commit()

# if __name__ == "__main__":
#     cli()