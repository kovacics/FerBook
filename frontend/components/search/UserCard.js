import { Card, Typography } from 'antd';
import Link from 'next/link';
import React from 'react';

export function UserCard({ user }) {
  return (
    <Link
      href="/profiles/[id]"
      as={`/profiles/${user.id}`}
    >
      <a>
        <Card>
          <div className="user-card">
            <div className="profile-picture-wrapper">
              <img
                src={user.profilePictureUrl}
                style={{
                  borderRadius: '50%',
                  width: '100%',
                }}
              />
            </div>
            <div>
              <Typography.Title level={3}>
                {user.fullName}
              </Typography.Title>
              @{user.username}
            </div>
          </div>

          <style jsx>{`
            .user-card {
              display: flex;
              align-items: center;
            }

            .profile-picture-wrapper {
              width: 100px;
              margin-right: 30px;
            }
          `}</style>
        </Card>
      </a>
    </Link>
  );
}
