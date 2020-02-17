CREATE TABLE "Users" (
  "ID" SERIAL PRIMARY KEY,
  "username" varchar UNIQUE,
  "password_hash" varchar NOT NULL,
  "first_name" varchar NOT NULL,
  "last_name" varchar NOT NULL,
  "date_of_birth" date,
  "admin" boolean,
  "confirmation_token" varchar UNIQUE,
  "confirmed_at" timestamp,
  "created_at" timestamp NOT NULL,
  "email" varchar UNIQUE NOT NULL,
  "picture_URL" varchar
);

CREATE TABLE "Friendships" (
  "ID" SERIAL PRIMARY KEY,
  "user_ID" int NOT NULL,
  "friend_ID" int NOT NULL,
  "accepted_invitation_at" timestamp
);

CREATE TABLE "Activities" (
  "ID" SERIAL PRIMARY KEY,
  "user_ID" int NOT NULL,
  "content" text NOT NULL,
  "picture_URL" varchar,
  "reciever_ID" int NOT NULL,
  "created_at" timestamp NOT NULL
);

CREATE TABLE "ActivityReactions" (
  "ID" SERIAL PRIMARY KEY,
  "activity_ID" int NOT NULL,
  "reaction_ID" int NOT NULL,
  "user_ID" int NOT NULL
);

CREATE TABLE "Chats" (
  "ID" SERIAL PRIMARY KEY,
  "created_at" timestamp NOT NULL,
  "name" varchar,
  "picture_URL" varchar
);

CREATE TABLE "ChatMessages" (
  "ID" SERIAL PRIMARY KEY,
  "chat_user_ID" int NOT NULL,
  "chat_ID" int NOT NULL,
  "sent_at" timestamp NOT NULL,
  "content" text NOT NULL
);

CREATE TABLE "ChatUsers" (
  "ID" SERIAL PRIMARY KEY,
  "chat_ID" int NOT NULL,
  "user_ID" int NOT NULL,
  "joined_at" timestamp NOT NULL
);

CREATE TABLE "Reactions" (
  "ID" SERIAL PRIMARY KEY,
  "reaction_type" varchar NOT NULL
);

ALTER TABLE "Friendships" ADD FOREIGN KEY ("user_ID") REFERENCES "Users" ("ID");

ALTER TABLE "Friendships" ADD FOREIGN KEY ("friend_ID") REFERENCES "Users" ("ID");

ALTER TABLE "Activities" ADD FOREIGN KEY ("user_ID") REFERENCES "Users" ("ID");

ALTER TABLE "Activities" ADD FOREIGN KEY ("reciever_ID") REFERENCES "Users" ("ID");

ALTER TABLE "ActivityReactions" ADD FOREIGN KEY ("activity_ID") REFERENCES "Activities" ("ID");

ALTER TABLE "ActivityReactions" ADD FOREIGN KEY ("reaction_ID") REFERENCES "Reactions" ("ID");

ALTER TABLE "ActivityReactions" ADD FOREIGN KEY ("user_ID") REFERENCES "Users" ("ID");

ALTER TABLE "ChatMessages" ADD FOREIGN KEY ("chat_user_ID") REFERENCES "ChatUsers" ("ID");

ALTER TABLE "ChatMessages" ADD FOREIGN KEY ("chat_ID") REFERENCES "Chats" ("ID");

ALTER TABLE "ChatUsers" ADD FOREIGN KEY ("chat_ID") REFERENCES "Chats" ("ID");

ALTER TABLE "ChatUsers" ADD FOREIGN KEY ("user_ID") REFERENCES "Users" ("ID");
