create table fa_tumblr_account (
  id VARCHAR(50) NOT NULL,
  acc_name VARCHAR(255) NOT NULL,
  acc_type VARCHAR(255) NOT NULL,
  popularity INTEGER NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fa_tumbr_account_unique_acc_name UNIQUE(acc_name)
);