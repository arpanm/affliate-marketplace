import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import VideoPost from './video-post';
import VideoPostDetail from './video-post-detail';
import VideoPostUpdate from './video-post-update';
import VideoPostDeleteDialog from './video-post-delete-dialog';

const VideoPostRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<VideoPost />} />
    <Route path="new" element={<VideoPostUpdate />} />
    <Route path=":id">
      <Route index element={<VideoPostDetail />} />
      <Route path="edit" element={<VideoPostUpdate />} />
      <Route path="delete" element={<VideoPostDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default VideoPostRoutes;
