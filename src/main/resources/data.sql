INSERT INTO space(label)
values ('default')
ON CONFLICT DO NOTHING;