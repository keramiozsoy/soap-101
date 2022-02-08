ftp_install:
	docker run \
            -v $HOME/localftp:/home/foo/upload \
            -p 2222:22 -d atmoz/sftp \
            foo:pass:1001

ftp_connect:
	sftp -oPort=2222 foo@localhost

ftp_put_example_file:
	put /localftp/test.txt /home/foo/upload/

.PHONY: ftp_install