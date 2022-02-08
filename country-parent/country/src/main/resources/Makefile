sftp_install:
	docker run -v $HOME/xmlUploaded:/home/foo/upload -p 2222:22 -d atmoz/sftp foo:pass

sftp_connect:
	sftp -oPort=2222 foo@localhost

sftp_list:
	ls $HOME/xmlUploaded